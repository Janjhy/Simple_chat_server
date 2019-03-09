import java.io.PrintStream
import java.net.ServerSocket
import java.util.*

fun main(args: Array<String>) {
    val ss = ServerSocket(80)
    println("We have port" + ss.localPort)

    while(true){
        println("Step 1")
        val s = ss.accept()
        println("Step 2")

        val scanner = Scanner(s.getInputStream())
        val printer = PrintStream(s.getOutputStream(), true)

        while(true){
            println("Step 3")
            val input = scanner.nextLine()
            printer.println("Hello $input")
            if(input == "quit") break

        }

        scanner.close() //closes the underlying socket as well
        println("Step 4")
    }
}