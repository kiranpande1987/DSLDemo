package dsl

class PersonBuilder
{
    var firstName: String = ""
    var lastName: String = ""

    fun build(): Person = Person(firstName, lastName)
}

fun person(lambda: PersonBuilder.() -> Unit): Person = PersonBuilder().apply(lambda).build()

class EmployeeBuilder
{
    var person: Person = Person()
    var project: String = ""

    var loc = Location()

    fun person(lambda: PersonBuilder.() -> Unit)
    {
        person = PersonBuilder().apply(lambda).build()
    }

    fun person(person: Person): Employee {
        this.person = person

        return build()
    }

    fun location(lambda: LocationBuilder.() -> Unit)
    {
        loc = LocationBuilder().apply(lambda).build()
    }

    fun build(): Employee = Employee(person.firstName, person.lastName, project).apply { location = loc }
}

class LocationBuilder
{
    var city: String = "NA"
    fun build(): Location = Location(city)
}

fun employee(lambda: EmployeeBuilder.() -> Unit): Employee = EmployeeBuilder().apply(lambda).build()
fun employee(person: Person, lambda: EmployeeBuilder.() -> Unit): Employee = EmployeeBuilder().apply(lambda).person(person)

class TeamBuilder
{
    var employees = ArrayList<Employee>()

    fun employee(block: EmployeeBuilder.() -> Unit) {
        employees.add(EmployeeBuilder().apply(block).build())
    }

    fun employee(person: Person, block: EmployeeBuilder.() -> Unit) {
        employees.add(EmployeeBuilder().apply(block).person(person))
    }

    fun employee(employee: Employee): Team {
        employees.add(employee)

        return build()
    }

    fun build(): Team = Team(employees)
}

fun team(lambda: TeamBuilder.() -> Unit): Team = TeamBuilder().apply(lambda).build()
fun team(employee: Employee, lambda: TeamBuilder.() -> Unit): Team = TeamBuilder().apply(lambda).employee(employee)

