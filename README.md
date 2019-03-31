# BeTheBee

*Greetings! My name is Jerimiah Fink, and I want to share with you my personal creed. What is the most admirable creature on God's green Earth? Why, it's the bee! Have you ever seen a bee on vacation? Have you ever seen a bee take a sick day? Well, my friends, the answer is no! So I say, be… the bee! Be the bee!*

----
## How to run (IntelliJ)

1. Clone and open project in IntelliJ.
2. Try to run Main (and fail).
3. Go to Run > Edit Configurations.
4. Set "Working directory" to "...\src\main\java\inf112\skeleton\app\assets".
5. Run successfully!

----

## Be the Bee Homie Tutorial: How to fix your tests:

1. In your *pom.xml*, between *\<dependencies>* *\</dependencies>*, add this:
```java
<dependency>
      <groupId>org.mockito</groupId>
      <artifactId>mockito-core</artifactId>
      <version>2.2.7</version>
      <scope>test</scope>
    </dependency>

    <dependency>
      <groupId>org.junit.jupiter</groupId>
      <artifactId>junit-jupiter-api</artifactId>
      <version>5.3.1</version>
      <scope>test</scope>
    </dependency>

    <dependency>
      <groupId>com.badlogicgames.gdx</groupId>
      <artifactId>gdx-backend-headless</artifactId>
      <version>1.9.9</version>
    </dependency>
```

2. Make a GameTest class:
https://github.com/inf112-v19/BeTheBee/blob/jarleDev/src/test/java/inf112/skeleton/app/GameTest.java

3. When making tests, extend GameTest, a la
` public class PlayerActionTest extends GameTest {}`

## Links

Google Docs:
https://docs.google.com/document/d/1yfMfC7k1WYnd7LHV9xSt__5PsQecMtFUssJk55mtu38/edit

