package com.chillandcode.collections_kotlin

import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import java.util.*

class MainActivity() : AppCompatActivity() {
    private var text: String = "Hello";
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        runTests()
        findViewById<TextView>(R.id.textView).text = text
    }

    private fun runTests() {
        //** this is a list
        //A list can have same elements that is duplicates are possible

        val numbers1 = listOf(0, 1, 2, 3, 4, 0, 1, 2, 3, 4, 5, 20, 30)
        append("# List :$numbers1")

        //** this is a set
        //A set will have only unique elements just like in maths
        //duplicate values are not possible

        val numbers2 = numbers1.toSet()
        append("# Set\nlist converted to Set :$numbers2")

        //if we try creating a set with similar values
        //it automatically removes the ones which are duplicates
        val numbers3 = setOf(1, 2, 1)
        append("Init set using  setOf(1, 2, 1) gives : $numbers3")

        val peopleAges = mutableMapOf<String, Int>("Fred" to 25, "Amy" to 23)
        append("A Mutable Map created using mutableMapOf<String, Int>(\"Fred\" to 25, \"Amy\" to 23) as :$peopleAges")

        peopleAges.put("Jen", 3)
        append("# Map.put method\n Running peopleAges.put(\"Jen\", 3) gives:$peopleAges")

        append("Trying Using assignment to create a new item: peopleAges[\"Sima\"] = 22 ")
        peopleAges["Sima"] = 22
        append(
            "result :$peopleAges"
        )

        append("# forEach: \npeopleAges.forEach{append(\"\${it.key} is \${it.value}\")}")
        //The forEach function gets called on the peopleAges variable and uses the code in the curly braces.
        peopleAges.forEach { append("${it.key} is ${it.value}") }

        append("# Map and Join to String usage: \npeopleAges.map { \"\${it.key} is \${it.value}\" }.joinToString ( \", \" )")
        append(peopleAges.map { "${it.key} is ${it.value}" }.joinToString(", "))

        append("# Filter on map\n filtering names with less than 4 characters")
        val filteredNames = peopleAges.filter { it.key.length < 4 }
        append(filteredNames.toString())

        append("#Lambdas")
        //making a function to show triple amount the normal way
        append(
            "1. #Normal Way-normalTriple Function : \nfun tripleNormal(num:Int):Int{\n" +
                    "        return num*3\n" +
                    "    } "
        )
        append("tripleNormal(3),Result  : ${tripleNormal(3)}")
        append("2. #Using Lambda function :\nval tripleLambda:(Int)->Int={a:Int->a*3}")
        append("tripleLambda(3),Result :\n${tripleLambda(3)} ")

        append("Tried using 2 arguments : \nval findSum:(Int,Int)->Int={a:Int,b:Int->a+b}")
        append("findSum(1,2) Result :${findSum(1, 2)}")

        append("Tried using 4 arguments and String return type : \nval getAverageOfFourNumbers:(Int,Int,Int,Int)->String={a:Int,b:Int,c:Int,d:Int->\"The average is :\${((a + b) + (c + d)) / 2}\"}")
        append(
            "Ran getAverageOfFourNumbers(1,2,3,4) ,\n Result is :${
                getAverageOfFourNumbers(
                    1,
                    2,
                    3,
                    4
                )
            }"
        )

        append("3. #Using more concise lambda function \n val moreConciseLambdaTriple:(Int)->Int={it*3}")
        append("moreConciseLambdaTriple(3) :\n Result is :${moreConciseLambdaTriple(3)}")

        append("Another TOPIC \n # Higher Order Functions : passing a lambda function to another function as parameter :")


        append("Normal Function\n Let  peopleNames = listOf(\"Fred\", \"Ann\", \"Barbara\", \"Joe\")")
        val peopleNames = listOf("Fred", "Ann", "Barbara", "Joe")
        append("Sorted Names alphabetical:\npeopleNames.sorted() Result : " + peopleNames.sorted())

        append(
            "Sorted names by length of characters- USING HIGHER ORDER FUNCTIONS WHICH TAKES FUNCTIONS AS THE INPUT\n peopleNames.sortedWith{str1:String,str2:String->str1.length-str2.length}\n Old: $peopleNames \nResult:  " +
                    "${peopleNames.sortedWith { str1: String, str2: String -> str1.length - str2.length }}"

            //sortedWidth iterates through two strings inside the peopleNames and compares the length of the two, sorts and returns the sorted list
            //Explained in following
        )
        //"${peopleNames.sortedWith { str1: String, str2: String -> str1.length - str2.length }}")
        // { str1: String, str2: String -> str1.length - str2.length } this represents comparator here

        // it compares the strings and return -1 0 or 1 according to status of two values like less than,equal to or greater than
        val temp1: Comparator<in Int>//Ctr+click on comparator to see details
        val temp2: Comparator<in String>//It can be done with various types



        append("//  SIMILAR => Onclick functions uses this Higher order functions to pass actions or functions to be run on click into the onclick listener as follows ")
        findViewById<TextView>(R.id.textView).setOnClickListener {
            snackHai(
                it,
                "Hai from Higher order function inside the on click listener"
            )
        }

        append("function: findViewById<TextView>(R.id.textView).setOnClickListener { snackHai(it) } \n\nIt here refers to the view, we can see that we are passing a function to an on click listener \n click on anywhere on the screen to test ")



        //another example of Higher order function
        val editText = findViewById<EditText>(R.id.editText)
        editText.setOnKeyListener { view, keyCode, event -> false//returning false hides the keyboard
        }

//MAKE WORD LISTS CODE LAB PART - 5
        wordGame()


    }
//HANDLE KEY EVENT IN SEPARATE FUNCTION AND ADD MORE CONDITIONS OR OTHER CODES
//    editText.setOnKeyListener { view, keyCode, event -> handleKeyEvent(view, keyCode)
//        false }
//    private fun handleKeyEvent(view: View?, keyCode: Int): Boolean {
//        return false//if false returned keyboard will hide else nothing happens
//    }

    private fun snackHai(view: View, message: String) {
        Snackbar.make(view, message, Snackbar.LENGTH_SHORT).show()
    }

    private fun tripleNormal(number: Int): Int {
        return number * 3
    }

    // this is also a simplification of the method in the normal form
    //when there is single argument to a function we may define it this way to call a function on the argument itself instead of passing it as an argument
    //    append("Result  : ${3.tripleNormal()}")
    //    private fun Int.tripleNormal(): Int {
    //        return this * 3
    //    }


    // instead of defining it as a function using "private fun"
    //we use the lambda function as a variable/val
    //where the value of the variable/val will be calculated as the returned value of the arrow function
    val tripleLambda: (Int) -> Int = { a: Int -> a * 3 }
    //^                 ^     ^   ^  ^    ^
    //1                 2     3   4  5    6
    // 1- is the lambda function
    // 2- is the argument type and signature of the lambda function
    // 3- is the return type
    // 4- is the arguments name
    // 5- is the type of the argument
    // 5- is the return value


    //lets look into another example with two arguments
    val findSum: (Int, Int) -> Int = { a: Int, b: Int -> a + b }

    //another example with 4 arguments and string return type
    private val getAverageOfFourNumbers: (Int, Int, Int, Int) -> String =
        { a: Int, b: Int, c: Int, d: Int -> "The average is :${((a + b) + (c + d)) / 2}" }

    val moreConciseLambdaTriple: (Int) -> Int = { it * 3 }
    private fun append(result: String) {
        text += "\n **********************************\n$result\n"

    }

    private fun wordGame(){
        val words= listOf<String>("Hello","What","Where","How","Guess")
        append("words : $words")
        val filteredWords=words.filter{it.startsWith("h",ignoreCase = true)}
        append("running filter : as a Higher order function on words :\nwords.filter{it.startsWith(\"b\",ignoreCase = true)} \n result : $filteredWords")

            append("filteredWords.shuffled().take(1) :  ${filteredWords.shuffled().take(1)}")
    }
}