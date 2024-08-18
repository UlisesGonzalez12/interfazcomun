// Definición de la interfaz común
interface IBaseNumber {
    val value: Int
    fun printValue()
}

// Clasepara números primos
class PrimeNumber(override val value: Int) : IBaseNumber {
    override fun printValue() {
        println("Prime number: $value")
    }
}

// Clase para números impares
class OddNumber(override val value: Int, val divisors: List<Int>) : IBaseNumber {
    override fun printValue() {
        println("Odd number: $value, Divisors: $divisors")
    }
}

// Clase para números pares
class EvenNumber(override val value: Int, val divisors: List<Int>) : IBaseNumber {
    override fun printValue() {
        println("Even number: $value, Divisors: $divisors")
    }
}

// Clase para procesar y clasificar números
class PrimeNumberProcessor(range: IntRange) {
    private val primes = mutableListOf<PrimeNumber>()
    private val odds = mutableListOf<OddNumber>()
    private val evens = mutableListOf<EvenNumber>()

    init {
        for (number in range) {
            val divisors = (2 until number).filter { number % it == 0 }
            when {
                number.isPrime() -> primes.add(PrimeNumber(number))
                number % 2 != 0 -> odds.add(OddNumber(number, divisors))
                else -> evens.add(EvenNumber(number, divisors))
            }
        }
    }

    private fun Int.isPrime(): Boolean = this > 1 && (2 until this).none { this % it == 0 }

    fun printNumbers() {
        primes.forEach { it.printValue() }
        odds.forEach { it.printValue() }
        evens.forEach { it.printValue() }
    }
}

// Ejecutar la clasificación
fun main() {
    val processor = PrimeNumberProcessor(1..100)
    processor.printNumbers()
}
