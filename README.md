# JavaExam
<table>
  <tr>
    <td>
      Simple java application for tracking transactions on a trading account with mock Products, Portfolio, Exchanges and customized exceptions
    </td>
  </tr>
</table>

# Built with:
* Java
* Mockito
* Gradle

# Getting Started
## What you'll need 
* A favorite text editor or IDE
* JDK 8 or later
* Install Gradle (https://gradle.org/install/)

Clone this repo to your desktop

    cd JavaExam 
  
  and run 
  
    ./gradlew build 
  
  To start the app run 
  
    ./gradlew run

# Usage
There are Mockito tests in /src/test/java/com/javaexam/MontrealTradeBagTest.java with some examples of the app in use

E.g:

    private List<Product> sampleProducts = new ArrayList<>(4);
    MontrealTradeBag todayBag;

    @Test(expected = ProductAlreadyRegisteredException.class)
    public void testAddingExistingProduct() throws ProductAlreadyRegisteredException{
        todayBag.addNewProduct(sampleProducts.get(0));
        todayBag.addNewProduct(sampleProducts.get(0));
    }
