/*-
 * #%L
 * utils-commons
 * %%
 * Copyright (C) 2016 - 2018 Gilles Landel
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */
package fr.landel.utils.commons;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import fr.landel.utils.commons.builder.ToStringBuilder;

/**
 * A basic class to manage Maven version in comparator. This class allows to
 * compare version like Maven but also manages other formats (ex: 1.0.5.3.4.41 ;
 * it's managed by Maven as a {@link String} not by this class).
 *
 * @since Nov 15, 2016
 * @author Gilles
 *
 */
public class Version implements Comparable<Version> {
    private static final Pattern VERSION_PATTERN = Pattern.compile("([\\d\\w]+)");
    private static final String SNAPSHOT = "SNAPSHOT";

    private final String version;
    private final Group[] array;

    public Version(final String version) {
        Objects.requireNonNull(version, "Version parameter cannot be null");

        this.version = version;
        this.array = this.getArray();
    }

    public String getVersion() {
        return this.version;
    }

    @Override
    public int compareTo(final Version v2) {
        final Group[] a1 = this.array;
        final Group[] a2 = v2.array;

        for (int i = 0; i < a1.length || i < a2.length; ++i) {
            if (i < a1.length && i < a2.length) {
                Group group1 = a1[i];
                Group group2 = a2[i];

                if (!group1.equals(group2)) {
                    boolean digit1 = group1.isDigits();
                    boolean digit2 = group2.isDigits();
                    boolean isSnapshot1 = !digit1 && group1.isSnapshot();
                    boolean isSnapshot2 = !digit2 && group2.isSnapshot();

                    if (digit1 && digit2) {
                        return Long.compare(group1.getNumber(), group2.getNumber());
                    } else if (digit1) {
                        if (!isSnapshot2 && group1.getNumber() == 0) {
                            return -1;
                        } else {
                            return 1;
                        }
                    } else if (digit2) {
                        if (!isSnapshot1 && group2.getNumber() == 0) {
                            return 1;
                        } else {
                            return -1;
                        }
                    } else if (isSnapshot1) {
                        return -1;
                    } else if (isSnapshot2) {
                        return 1;
                    } else {
                        return group1.getContent().compareTo(group2.getContent());
                    }
                }
            } else if (i < a1.length) {
                Group group1 = a1[i];

                if (group1.isSnapshot()) {
                    return -1;
                } else if (group1.isDigits()) {
                    if (group1.getNumber() == 0) {
                        return 0;
                    }
                } else {
                    return -1;
                }
                return 1;
            } else {
                Group group2 = a2[i];

                if (group2.isSnapshot()) {
                    return 1;
                } else if (group2.isDigits()) {
                    if (group2.getNumber() == 0) {
                        return 0;
                    }
                } else {
                    return 1;
                }
                return -1;
            }
        }

        return 0;
    }

    private Group[] getArray() {
        final Matcher matcher = VERSION_PATTERN.matcher(this.version);
        final Group[] array = new Group[this.version.length()];
        int i = 0;
        while (matcher.find()) {
            final String content = matcher.group(1);
            long number;
            boolean snapshot = false;
            if (NumberUtils.isDigits(content)) {
                try {
                    number = Long.parseLong(content);
                } catch (final NumberFormatException e) {
                    number = -1;
                }
            } else {
                number = -1;
                snapshot = SNAPSHOT.equals(content);
            }
            array[i++] = new Group(content, number > -1, number, snapshot);
        }

        return ArrayUtils.subarray(array, 0, i);
    }

    @Override
    public String toString() {
        return this.getVersion();
    }

    /**
     * Group tuple
     * 
     * @since 17 janv. 2018
     * @author Gilles
     *
     */
    static class Group {

        private final String content;
        private final boolean digits;
        private final long number;
        private final boolean snapshot;

        /**
         * Constructor
         *
         * @param content
         *            the group content
         * @param digits
         *            true, if the content is a number
         * @param number
         *            the parsed group
         * @param snapshot
         *            true, if the group is SNAPSHOT
         * @category constructor
         */
        public Group(final String content, final boolean digits, final long number, final boolean snapshot) {
            this.content = content;
            this.digits = digits;
            this.number = number;
            this.snapshot = snapshot;
        }

        /**
         * @return the content
         */
        public String getContent() {
            return this.content;
        }

        /**
         * @return the digit
         */
        public boolean isDigits() {
            return this.digits;
        }

        /**
         * @return the number
         */
        public long getNumber() {
            return this.number;
        }

        /**
         * @return the snapshot
         */
        public boolean isSnapshot() {
            return this.snapshot;
        }

        @Override
        public boolean equals(Object obj) {
            return obj != null && this.getContent().equals(((Group) obj).getContent());
        }

        @Override
        public int hashCode() {
            return this.getContent().hashCode();
        }

        @Override
        public String toString() {
            // @formatter:off
            return new ToStringBuilder("Group")
                    .append("content", this.getContent())
                    .append("digits", this.isDigits())
                    .append("number", this.getNumber())
                    .append("snapshot", this.isSnapshot())
                    .build();
         // @formatter:on
        }
    }
}