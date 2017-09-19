# utils-commons

[![Build Status](https://api.travis-ci.org/Gilandel/utils-commons.svg?branch=master)](https://travis-ci.org/Gilandel/utils-commons/builds)
[![Codacy Badge](https://api.codacy.com/project/badge/grade/8f3069e16a174fecaeeaff3cd742414b)](https://www.codacy.com/app/gilles/utils-commons)
[![Dependency Status](https://www.versioneye.com/user/projects/58b29b6f7b9e15003a17e544/badge.svg?style=flat)](https://www.versioneye.com/user/projects/58b29b6f7b9e15003a17e544)
[![codecov.io](https://codecov.io/github/Gilandel/utils-commons/coverage.svg?branch=master)](https://codecov.io/github/Gilandel/utils-commons?branch=master)
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/fr.landel.utils/utils-commons/badge.svg)](https://maven-badges.herokuapp.com/maven-central/fr.landel.utils/utils-commons)
[![Javadocs](http://www.javadoc.io/badge/fr.landel.utils/utils-commons.svg)](http://www.javadoc.io/doc/fr.landel.utils/utils-commons)

[![Tokei LoC](https://tokei.rs/b1/github/Gilandel/utils-commons)](https://github.com/Aaronepower/tokei)
[![Tokei NoFiles](https://tokei.rs/b1/github/Gilandel/utils-commons?category=files)](https://github.com/Aaronepower/tokei)
[![Tokei LoComments](https://tokei.rs/b1/github/Gilandel/utils-commons?category=comments)](https://github.com/Aaronepower/tokei)

[![codecov.io tree](https://codecov.io/gh/Gilandel/utils-commons/branch/master/graphs/tree.svg)](https://codecov.io/gh/Gilandel/utils-commons/branch/master)
[![codecov.io sunburst](https://codecov.io/gh/Gilandel/utils-commons/branch/master/graphs/sunburst.svg)](https://codecov.io/gh/Gilandel/utils-commons/branch/master)

Work progress:
![Code status](http://vbc3.com/script/progressbar.php?text=Code&progress=100)
![Test status](http://vbc3.com/script/progressbar.php?text=Test&progress=100)
![Benchmark status](http://vbc3.com/script/progressbar.php?text=Benchmark&progress=100)
![JavaDoc status](http://vbc3.com/script/progressbar.php?text=JavaDoc&progress=100)

```xml
<dependency>
	<groupId>fr.landel.utils</groupId>
	<artifactId>utils-commons</artifactId>
	<version>1.0.11</version>
</dependency>
```

## Summary

1. [Summary](#summary)
1. [Commons](#commons)
   1. [Default](#default)
   1. [Result](#result)
1. [Builder](#builder)
   1. [EqualsBuilder](#equalsbuilder)
   1. [EqualsBuilder2](#equalsbuilder2)
   1. [HashCodeBuilder](#hashcodebuilder)
   1. [HashCodeBuilder2](#hashcodebuilder2)
   1. [ToStringBuilder](#tostringbuilder)
1. [Expect](#expect)
1. [Exception](#exception)
1. [Function](#function)
1. [Listener](#listener)
1. [Over](#over)
1. [Tuple](#tuple)
1. [License](#license)

## Commons
- ArrayUtils: Extends ArrayUtils from Apache project, adds methods to check array,
- AsciiUtils: Simple class to check if a character is an numeric or an alpha character,
- CastUtils: To cast map / list / object into typed objects,
- ClassUtils: To get super classes or to get common super classes,
- CollectionUtils2: Adds missing transform methods (in complement of CollectionUtils provided by Apache Team),
- Comparators: Basics comparators (Byte, Short, Long, Float, Double, BigInteger BigDecimal, Char, String, Date, Calendar, Duration, Instant, OffsetTime, OffsetDateTime, LocalTime, LocalDateTime, ZonedDateTime) and Maven version comparator,
- DateUtils: Extends DateUtils from Apache project, adds methods to get date if null, getDate wrapper to secure date transfer and between to calculate time between dates,
- Default: A class like Optional, but if a null value is set or is defined as empty, this method returns the default value,
- EnumChar: A list of ASCII characters and others with their unicode and HTML version,
- EnumUtils: Extends EnumUtils from Apache project, adds methods to get null if empty name is used (to avoid exception)
- HexUtils: To convert hexadecimal in bytes,
- MapUtils2: Helper class to create Map,
- NumberUtils: Extends NumberUtils from Apache project, adds methods to check number equality,
- ObjectUtils: Extends ObjectUtils from Apache project, adds features for Object,
- Result: A class like Optional, but if a null value is set (not empty), this method returns 'present', the aim is to differentiate an empty value and a null,
- StreamUtils: Provides function to manage composed Java 8 functions
- StringUtils: Extends StringUtils from Apache project, add methods to get default string if empty or null and add methods like 'inject' to insert arguments into a string.

### Default

```java
Default.empty(defaultText).get(); // => returns 'defaultText' content ('defaultText' cannot be null)
Default.empty(defaultText).isPresent(); // => returns 'false'
Default.empty(defaultText).ifPresent(consumer); // does nothing
Default.empty(defaultText).ifAbsent(consumer); // executes the consumer (on the default value)

Default.of(text).get(); // => returns 'text' content ('text' cannot be null)
Default.of(text).isPresent(); // => returns 'true'
Default.of(text).ifPresent(consumer); // executes the consumer (on the value)
Default.of(text).ifAbsent(consumer); // does nothing

Default.ofNullable(text, defaultText).get();
// => if 'text' is not null, returns 'text' content, otherwise returns 'defaultText' content ('text' may be null, 'defaultText' cannot be null)
Default.ofNullable(text, defaultText).isPresent(); // => returns 'true' or 'false'
Default.ofNullable(text, defaultText).ifPresent(consumer); // executes the consumer if 'text' is not null (on the value)
Default.ofNullable(text, defaultText).ifAbsent(consumer); // executes the consumer if 'text' is null (on the default value)
```

### Result

```java
Result.empty().isPresent(); // => returns 'false'
Result.empty().isNull(); // => returns 'true'
Result.empty().ifPresent(consumer); // does nothing
Result.empty().ifNotNull(consumer); // does nothing

Result.of(text).isPresent(); // => returns 'true' ('text' cannot be null)
Result.of(text).isNull(); // => returns 'false'
Result.of(text).ifPresent(consumer); // executes the consumer
Result.of(text).ifNotNull(consumer); // executes the consumer

Result.ofNullable(text).isPresent(); // => returns 'true' ('text' may be null)
Result.ofNullable(text).isNull(); // => returns 'true' or 'false'
Result.ofNullable(text).ifPresent(consumer); // executes the consumer
Result.ofNullable(text).ifNotNull(consumer); // executes the consumer if 'text' is not null
```

## Builder
- EqualsBuilder: Extends EqualsBuilder from Apache project, allows to append a property through a functional getter function,
- EqualsBuilder2: Based on the fact that the most of the time I compare DTOs, the class provide a constructor for both checked objects and appenders based on these objects with the ability to check them througth functional getters and predicates.
- HashCodeBuilder: Extends EqualsBuilder from Apache project, allows to append a property through a functional getter function,
- ToStringBuilder: Another version of the ToStringBuilder, simple and also faster.

### EqualsBuilder

```java
new EqualsBuilder()
	.append(dto1, dto2, dto -> dto.getId())
	.isEqual();
```

### EqualsBuilder2

```java
// To check 2 DTOs with 2 properties (id and name)
// Here we check, if both ids are equals (or both null)
// We also check if both names are equals (ignoring case consideration),
// previous calling the predicate function, a null check is automatically performed to avoid NullPointerException

new EqualsBuilder2<>(dto1, dto2)
	.append(dto -> dto.getId())
	.append(dto -> dto.getName(), (name1, name2) -> name1.equalsIgnoreCase(name2))
	.isEqual();

// dto1 = null, dto2 = {id:1, name:TesT} => false
// dto1 = {id:1, name:test}, dto2 = null => false
// dto1 = {id:1, name:test}, dto2 = {id:1, name:TesT} => true
// dto1 = {id:1, name:test}, dto2 = {id:2, name:TesT} => false
// dto1 = {id:null, name:test}, dto2 = {id:1, name:TesT} => false
// dto1 = {id:null, name:test}, dto2 = {id:null, name:TesT} => true
// dto1 = {id:null, name:null}, dto2 = {id:null, name:TesT} => false
// dto1 = {id:null, name:null}, dto2 = {id:1, name:null} => true

// Other example:

new EqualsBuilder2<>(dto1, dto2)
	.append(dto -> dto.getId())
	.append(dto -> dto.getName(), (name1, name2) -> name1.equalsIgnoreCase(name2))
	.and(dto1, dto3, CommonInterface.class)
	.append(dto -> dto.getDescription())
	.isEqual();

// In this case, the append on 'dto1' and 'dto3' is based on the common method defined in 'CommonInterface'

// If 'and' method is used to compare multiple objects, each step can be saved, the 'equals' is performed at the end.
// Indeed, only first checks are done at the beginning on objects (null, instance equals, comparable class), all appended checks are done on 'isEqual' call.

EqualsBuilder2<Dto1Type, Dto3Type> builder = new EqualsBuilder2<>(dto1, dto2)
	.append(dto -> dto.getId())
	.append(dto -> dto.getName(), (name1, name2) -> name1.equalsIgnoreCase(name2))
	.and(dto1, dto3, CommonInterface.class)
	.append(dto -> dto.getDescription());
	
dto1.setName(newName);

builder.isEqual(); // the equality will take account of the dto1 new name.
```

### HashCodeBuilder

```java
new HashCodeBuilder()
	.append(dto, dto -> dto.getId() * 37)
	.isEqual();
```

### HashCodeBuilder2

```java
new HashCodeBuilder2<>(this)
	.append(dto -> dto.getId() * 37)
	.isEqual();
```

### ToStringBuilder

```java
new ToStringBuilder(this, ToStringStyle.JSON)
	.append("key", value)
	.append("key", () -> value)
	.append("key", () -> value, text -> text.toUpperCase())
	.append("key", 126452.2223, ToStringBuilder.FORMATTER)
	.appendIfPresent("key", Optional.ofNullable(null))
	.build();
// =&gt; {"org.test.MyClass":{"key":"value","key":"value","key":"VALUE","key":"126 452.222"}}
```

## Expect
Validates a thrown exception and its message.

* Signatures:
	- exception(AssertConsumer<Throwable> consumer, Class<? extends Throwable> expectedException)
	- exception(AssertConsumer<Throwable> consumer, Class<? extends Throwable> expectedException, String expectedMessage)
	- exception(AssertConsumer<Throwable> consumer, Class<? extends Throwable> expectedException, final TriFunction<Boolean, String, String, E> exceptionFunction)
	- exception(AssertConsumer<Throwable> consumer, Class<? extends Throwable> expectedException, String expectedMessage, final TriFunction<Boolean, String, String, E> exceptionFunction)

* Parameters:
	- consumer: The consumer or where the code to check can be placed
	- expectedException: The class of expected exception
	- expectedMessage: The expected exception message
	- exceptionFunction: The exception builder, only called on mismatch. Has 3 parameters:
		- first: true, if the expected exception matches
		- second: the expected message
		- third: the actual message

* Prerequisites:
	- consumer NOT null
	- expectedException NOT null

* Examples:
```java
Expect.exception(() -> {
    // throw new IllegalArgumentException("parameter cannot be null");
    getMyType(null);
}, IllegalArgumentException.class); // -> OK

Expect.exception(() -> {
    // throw new IOException("parameter cannot be null");
    getMyType(null);
}, IllegalArgumentException.class); // -> throw an ExpectException



Expect.exception(() -> {
     // throw new IllegalArgumentException("parameter cannot be null");
     getMyType(null);
 }, IllegalArgumentException.class, "parameter cannot be null"); // -> OK
 
 Expect.exception(() -> {
     // throw new IllegalArgumentException("type cannot be null");
     getMyType(null);
 }, IllegalArgumentException.class, "parameter cannot be null");  // -> throw an ExpectException



// Obviously, you can save this in a static variable to share it.
// This function is not provided by module to avoid a JUnit dependency.
// ComparisonFailure come from: org.junit.ComparisonFailure

TriFunction<Boolean, String, String> junitError = (catched, expected, actual) -> {
    if (catched) {
        return new ComparisonFailure("The exception message don't match.", expected, actual);
    } else {
        return new AssertionError("The expected exception never came up");
    }
};



Expect.exception(() -> {
    // throw new IllegalArgumentException("parameter cannot be null");
    getMyType(null);
}, IllegalArgumentException.class, junitError); // -> OK

Expect.exception(() -> {
    // throw new IOException("parameter cannot be null");
    getMyType(null);
}, IllegalArgumentException.class, junitError); // -> throw an AssertionError


Expect.exception(() -> {
     // throw new IllegalArgumentException("parameter cannot be null");
     getMyType(null);
 }, IllegalArgumentException.class, "parameter cannot be null", junitError); // -> OK
 
 Expect.exception(() -> {
     // throw new IllegalArgumentException("type cannot be null");
     getMyType(null);
 }, IllegalArgumentException.class, "parameter cannot be null", junitError);  // -> throw a ComparisonFailure
```

## Exception
- AbstractException: Base class for Exception (add constructors to directly create message with arguments),
- AbstractException: Base class for RuntimeException (add constructors to directly create message with arguments),
- FunctionException: Runtime Exception thrown on error with throwable functional interfaces,
- IllegalOperationException: Specific Runtime Exception used by functional interface in case of unauthorized operation (mainly in QueryBuilder)
- ExceptionUtils: Extends ExceptionUtils from Apache project, add functions like exception supplier builder or get origin cause.

## Function
Functional interfaces that support to throw exception:
- ThrowableSupplier: To supply an exception (just throws the given exception),
- *Throwable: Functional interfaces that manage one parameters,
- Bi*Throwable: Functional interfaces that manage two parameters,
- Tri*Throwable: Functional interfaces that manage three parameters,
- Quad*Throwable: Functional interfaces that manage four parameters.

Also, missing standard functional interfaces are provided to manage 2, 3 and 4 parameters.

The catched exceptions (for *Throwable interfaces) are re-mapped into the original exception (but without warning) or by default into FunctionException (an extend of RuntimeException).

```java
// definition
void myMethod(ThrowableSupplier<MyException> throwableSupplier);

// call the method
myMethod(() -> throw new MyException());

// definition
void myMethod(SupplierThrowable<String, MyException> supplier);

// call the method
myMethod(() -> Optional.ofNullable(myString).orElseThrow(new MyException("myString cannot be null")));
```

## Listener
Very simple classes to manage events (listenable / event / listener).

## Over
- AbstractOverComparable: Class to force the implementation of compareTo method
- AbstractOverObject: Class to force implementation of toString, equals and hashCode 

## Tuple
In addition of tuples provided by the Apache Team, this package provides new tuples to create container object for 1, 2, 3 and 4 parameters.
Main entry points to use this, are the classes with the following names:
- Single: To create a container for an object (interesting when you use a variable in and out a functional interface),
- PairIso: To create a Pair of the same type,
- TripleIso: To create a Triple of the same type,
- Quad: To create a Quad of four different types,
- QuadIso: To create a Quad of the same type,
- Generic: To create a container of unlimited objects (the only purpose is in a Proof Of Concept context to differentiate a future DTO (post POC) with a list).

```java
Single<String> single = Single.of("v1");
PairIso<String> pairIso = PairIso.of("v1", "v2");
TripleIso<String> tripleIso = PairIso.of("v1", "v2", "v3");
QuadIso<String> quadIso = PairIso.of("v1", "v2", "v3", "v4");
Quad<String, String, String, Integer> quad = Quad.of("v1", "v2", "v3", 12);
Generic<String> generic = Generic.of("v1", "v2", "v3", "v4", "v5", "v6", "v7"); // ...

// A simple use case for Single (how to set a variable in a consumer usable after)
Color c = Color.BLACK;
final Single<Color> colorContainer = Single.ofMutable(c);
checker.test(v -> colorContainer.set(v));
LOGGER.info("My new color: {}", colorContainer.get());
```

## License
Apache License, version 2.0