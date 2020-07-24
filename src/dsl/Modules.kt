package dsl

open class Person(
    open var firstName: String = "",
    open var lastName: String = ""
)
{
    override fun toString(): String = "$firstName $lastName"
}

class Employee(
    override var firstName: String = "",
    override var lastName: String = "",
    var project: String = ""
): Person(firstName, lastName)
{
    var location: Location = Location()

    override fun toString(): String = "${super.toString()} in $project at ${location}"
}

class Location(var city: String = "NA")
{
    override fun toString(): String = city
}

class Team(var employees: ArrayList<Employee> = ArrayList())