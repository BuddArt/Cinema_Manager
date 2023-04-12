package cinema

fun main () {
    // Ввод конфигурации кинозала.
    println("Enter the number of rows:")
    print("> ")
    val rows = readln().toInt()
    println("Enter the number of seats in each row:")
    print("> ")
    val seats = readln().toInt()
    // Переменные общего количества мест, цен и схемы кинозала.
    var allseats = rows * seats
    var numbook = 0
    var curIncome = 0
    val price1 = 10
    val price2 = 8
    val priceAll = allseats * 10
    val priceAllEven = seats * ((rows / 2 * 10) + (rows / 2 * 8))
    val priceAllOdd = seats * ((rows / 2 * 10) + ((rows / 2 + 1) * 8))
    val cinema = MutableList(rows) { MutableList(seats) { 'S' } }
    // Цикл do-while и печать Меню.
    do {
        println("\n1. Show the seats")
        println("2. Buy a ticket")
        println("3. Statistics")
        println("0. Exit")
        print("> ")
        var input = readln().toInt()
        when (input) {
            // Печать конфигурации зала.
            1 -> {
                print("\nCinema:\n  ")
                for (i in 1..seats) print("$i ")
                for (i in cinema.indices) print("\n${i + 1} ${cinema[i].joinToString(" ")}")
                println("\n")
            }
            // Выбор места, печать цены за одно место, добавление забронированного места в конфигурацию зала.
            2 -> {
                var numErr = 0
                do {
                    try {
                        println("\nEnter a row number:")
                        print("> ")
                        val rowID = readln().toInt()
                        println("Enter a seat number in that row:")
                        print("> ")
                        val seatID = readln().toInt()
                        if( rowID > rows || seatID > seats || seatID <= 0 || rowID <= 0 ) {
                            println("\nWrong input!")
                            numErr = 0 }
                        if (cinema[rowID - 1][seatID - 1] == 'B') {
                            println("\nThat ticket has already been purchased!")
                            numErr = 0
                        } else {
                            numErr = 1
                            if (allseats <= 60) {
                                println("\nTicket price: $$price1")
                                curIncome += 10
                            } else if (allseats > 60) {
                                if (rowID <= rows / 2) {
                                    println("\nTicket price: $$price1")
                                    curIncome += 10
                                } else if (rowID > rows / 2) {
                                    println("\nTicket price: $$price2")
                                    curIncome += 8
                                }
                            }
                        }
                        cinema[rowID - 1][seatID - 1] = 'B' //Добавление забронированного места в конфигурацию зала.
                    } catch (e: IndexOutOfBoundsException) {}
                } while (numErr == 0)
                numbook++
            }
            3 -> {
                var percentage = 0.0
                percentage = ((numbook.toDouble() * 100.00)/allseats.toDouble())
                var formatPercentage = "%.2f".format(percentage)


                println("\nNumber of purchased tickets: $numbook")
                println("Percentage: $formatPercentage%")
                println("Current income: $$curIncome")
                print("Total income: $")
                if (allseats <= 60) {
                    println(priceAll)
                } else if (allseats > 60) {
                    if (rows % 2 == 0) {
                        println(priceAllEven)
                    } else if (rows % 2 == 1) {
                        println(priceAllOdd)
                    }
                }
            }
        }} while (input != 0) //Конец цикла при вызове 0.
}