package dsl

fun main()
{
    var kiran = person {
        firstName = "Kiran"
        lastName = "Pande"
    }

    println(kiran)

    var emp1 = employee {
        person {
            firstName = "Kiran"
            lastName = "Pande"
        }

        location {
            city = "Pune"
        }

        project = "Project One"
    }

    println(emp1)

    var emp2 = employee(kiran) {
        project = "Project Two"

        location { city = "Mumbai" }
    }

    println(emp2)
    println()

    var team = team {
                    employee {
                        person {
                            firstName = "Anshu"
                            lastName = "Vij"
                        }

                        project = "Project Three"

                        location { city = "Delhi" }
                    }

                    employee(kiran) {
                        project = "Project Four"

                        location { city = "Chennai" }
                    }

                    employee(emp2)
                }

    team.employees.forEach { println(it) }
    println()

    var team2 = team {
        employee(emp1)
        employee(emp2)
    }.employees.forEach { println(it) }
}


