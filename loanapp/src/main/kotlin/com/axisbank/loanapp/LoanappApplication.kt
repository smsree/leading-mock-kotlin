package com.axisbank.loanapp

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class LoanappApplication

fun main(args: Array<String>) {
	runApplication<LoanappApplication>(*args)
}
