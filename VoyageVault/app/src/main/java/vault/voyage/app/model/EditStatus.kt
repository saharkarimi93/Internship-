package vault.voyage.app.model

enum class EditStatus(val name_:String) {
    USERNAME("Username"),
    PASSWORD("Password"),
    EMAIL("Email"),
    FIRSTNAME("FirstName"),
    LASTNAME("LastName"),
    NUMBER("Number");

    override fun toString(): String {
        return name_
    }



}