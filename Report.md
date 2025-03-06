# Report

Submitted report to be manually graded. We encourage you to review the report as you read through the provided
code as it is meant to help you understand some of the concepts. 

## Technical Questions

1. What is the difference between == and .equals in java? Provide a code example of each, where they would return different results for an object. Include the code snippet using the hash marks (```) to create a code block.

   <br> In Java, == is used for comparing primitive types for equality. When used with objects, == compares reference values (memory addresses), meaning it checks whether two references point to the same object in memory.
   The .equals() method, inherited from the Object class, is used for content comparison. By default, .equals() behaves like ==, but we can override it in a subclass to compare attributes instead of memory locations.
   
   ```java    
   class Car {
       String color;
       int year;
   
       public Car(String color, int year) {
           this.color = color;
           this.year = year;               
       }
   
       @Override
       public boolean equals(Object obj) {
           if (this == obj) return true;  // Same reference
           if (obj == null || getClass() != obj.getClass()) return false;
           Car car = (Car) obj;
           return this.year == car.year && this.color.equals(car.color);
       }
   }
   
   public class Main {
       public static void main(String[] args) {
           Car car1 = new Car("Red", 2023);
           Car car2 = new Car("Red", 2023);
   
           // false, because car1 and car2 are different objects in memory
           System.out.println(car1 == car2);
   
           // true, because we overrode equals() to compare attributes
           System.out.println(car1.equals(car2));
       }
   }
    ```
   
2. Logical sorting can be difficult when talking about case. For example, should "apple" come before "Banana" or after? How would you sort a list of strings in a case-insensitive manner?
   
   <br> In standard ASCII sorting, uppercase letters come before lowercase letters. This means that "Banana" would appear before "apple" in a regular sort.
   However, in case-insensitive sorting, we want to treat "apple" and "Banana" as if they were the same case. 
   This aligns with how words are ordered in dictionaries—where "apple" should come before "Banana", regardless of capitalization.




3. In our version of the solution, we had the following code (snippet)
    ```java
    public static Operations getOperatorFromStr(String str) {
        if (str.contains(">=")) {
            return Operations.GREATER_THAN_EQUALS;
        } else if (str.contains("<=")) {
            return Operations.LESS_THAN_EQUALS;
        } else if (str.contains(">")) {
            return Operations.GREATER_THAN;
        } else if (str.contains("<")) {
            return Operations.LESS_THAN;
        } else if (str.contains("=="))...
    ```
    Why would the order in which we checked matter (if it does matter)? Provide examples either way proving your point. 
    <br> <br> If we check for " > " before " >= ", then both conditions would match a string like " >= " because " >= " contains " > " as a substring.
    This would result in returning `Operations.GREATER_THAN`, which is a wrong Operations value. Therefore, to ensure the more specific operators, such as "==", ">=", "<=" get checked first.


4. What is the difference between a List and a Set in Java? When would you use one over the other? 
<br> In Java, a List is an ordered collection that allows duplicates and provides index-based access.
A Set is an unordered collection that does not allow duplicates. Use a List when the order and duplicates matter, 
and use a Set when the elements should be unique and do not require a specific order.


5. In [GamesLoader.java](src/main/java/student/GamesLoader.java), we use a Map to help figure out the columns. What is a map? Why would we use a Map here? 
<br> A `Map` is a data structure that stores data in key-value pairs, allowing efficient retrieval of values based on their keys. 
In `GamesLoader.java`, we use a Map to store column names as keys and their corresponding indices as values.
This approach allows us to quickly find the data we need.


6. [GameData.java](src/main/java/student/GameData.java) is actually an `enum` with special properties we added to help with column name mappings. What is an `enum` in Java? Why would we use it for this application?
<br> An `enum`(short for enumeration) in Java is used to define a finite set of named constant values.
Unlike regular constants (final static variables), an enum can also have fields, methods, and constructors, making it more powerful than a simple list of constants.
The reason for using `enum` in this application is the columns in the dataset are fixed and predefined.
Using `enum` ensures type safety, improves readability, and enhances maintainability.


7. Rewrite the following as an if else statement inside the empty code block.
    ```java
    switch (ct) {
                case CMD_QUESTION: // same as help
                case CMD_HELP:
                    processHelp();
                    break;
                case INVALID:
                default:
                    CONSOLE.printf("%s%n", ConsoleText.INVALID);
            }
    ``` 

    ```java
    // your code here, don't forget the class name that is dropped in the switch block..
    
    ```

## Deeper Thinking

ConsoleApp.java uses a .properties file that contains all the strings
that are displayed to the client. This is a common pattern in software development
as it can help localize the application for different languages. You can see this
talked about here on [Java Localization – Formatting Messages](https://www.baeldung.com/java-localization-messages-formatting).

Take time to look through the console.properties file, and change some of the messages to
another language (probably the welcome message is easier). It could even be a made up language and for this - and only this - alright to use a translator. See how the main program changes, but there are still limitations in 
the current layout. 

Post a copy of the run with the updated languages below this. Use three back ticks (```) to create a code block. 

```text
// your consoles output here
```

Now, thinking about localization - we have the question of why does it matter? The obvious
one is more about market share, but there may be other reasons.  I encourage
you to take time researching localization and the importance of having programs
flexible enough to be localized to different languages and cultures. Maybe pull up data on the
various spoken languages around the world? What about areas with internet access - do they match? Just some ideas to get you started. Another question you are welcome to talk about - what are the dangers of trying to localize your program and doing it wrong? Can you find any examples of that? Business marketing classes love to point out an example of a car name in Mexico that meant something very different in Spanish than it did in English - however [Snopes has shown that is a false tale](https://www.snopes.com/fact-check/chevrolet-nova-name-spanish/).  As a developer, what are some things you can do to reduce 'hick ups' when expanding your program to other languages?


As a reminder, deeper thinking questions are meant to require some research and to be answered in a paragraph for with references. The goal is to open up some of the discussion topics in CS, so you are better informed going into industry. 