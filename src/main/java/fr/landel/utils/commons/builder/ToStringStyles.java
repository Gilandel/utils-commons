/*-
 * #%L
 * utils-commons
 * %%
 * Copyright (C) 2016 - 2017 Gilles Landel
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
package fr.landel.utils.commons.builder;

import java.util.function.Supplier;

/**
 * {@link ToStringBuilder} style enumeration
 *
 * @since Mar 11, 2017
 * @author Gilles
 *
 */
public enum ToStringStyles {

    /**
     * The default toString style.
     * 
     * <pre>
     * test[java.awt.Color[r=0,g=0,b=0],0,blue=java.awt.Color[r=0,g=0,b=255],value=1 153 120 156,569,supplier,SUPPLIER,supplier=12,supplier=SUPPLIER,optional,OPTIONAL,optional=optional,optional=OPTIONAL]
     * </pre>
     */
    DEFAULT(ToStringStyleDefault::new),

    /**
     * The JSON toString style
     * 
     * <pre>
     * {test:{java.awt.Color[r=0,g=0,b=0],0,blue:java.awt.Color[r=0,g=0,b=255],value:1 153 120 156,569,supplier,SUPPLIER,supplier:12,supplier:SUPPLIER,optional,OPTIONAL,optional:optional,optional:OPTIONAL}}
     * </pre>
     */
    JSON(ToStringStyleJSON::new),

    /**
     * The JSON toString style with spaces
     * 
     * <pre>
     * {test: {java.awt.Color[r=0,g=0,b=0], 0, blue: java.awt.Color[r=0,g=0,b=255], value: 1 153 120 156, 569, supplier, SUPPLIER, supplier: 12, supplier: SUPPLIER, optional, OPTIONAL, optional: optional, optional: OPTIONAL}}
     * </pre>
     */
    JSON_SPACED(ToStringStyleJSONSpaced::new),

    /**
     * The JSON toString style with quotes
     * 
     * <pre>
     * {"test":{"java.awt.Color[r=0,g=0,b=0]","0","blue":"java.awt.Color[r=0,g=0,b=255]","value":"120 156,569","optional","OPTIONAL","optional":"optional","optional":"OPTIONAL"}}
     * </pre>
     */
    JSON_QUOTED(ToStringStyleJSONQuoted::new),

    /**
     * The readable toString style
     * 
     * <pre>
     * test = 
     * ['java.awt.Color[r=0,g=0,b=0]',
     * '0',
     * 'blue' = 'java.awt.Color[r=0,g=0,b=255]',
     * 'value' = '1 153 120 156,569',
     * 'supplier',
     * 'SUPPLIER',
     * 'supplier' = '12',
     * 'supplier' = 'SUPPLIER',
     * 'optional',
     * 'OPTIONAL',
     * 'optional' = 'optional',
     * 'optional' = 'OPTIONAL']
     * </pre>
     */
    READABLE(ToStringStyleReadable::new),

    /**
     * The JSON toString style
     * 
     * <pre>
     * (test: (java.awt.Color[r=0,g=0,b=0], 0, blue: java.awt.Color[r=0,g=0,b=255], value: 1 153 120 156, 569, supplier, SUPPLIER, supplier: 12, supplier: SUPPLIER, optional, OPTIONAL, optional: optional, optional: OPTIONAL))
     * </pre>
     */
    PARENTHESIS(ToStringStyleParenthesis::new);

    private Supplier<ToStringStyle> supplier;

    ToStringStyles(final Supplier<ToStringStyle> supplier) {
        this.supplier = supplier;
    }

    /**
     * @return the constructor supplier
     */
    public Supplier<ToStringStyle> getSupplier() {
        return this.supplier;
    }
}
