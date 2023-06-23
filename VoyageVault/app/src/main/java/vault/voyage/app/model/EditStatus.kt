package vault.voyage.app.model

enum class EditStatus(private val status:String) {
    USERNAME("Username"),
    PASSWORD("Password"),
    EMAIL("Email"),
    FIRSTNAME("FirstName"),
    LASTNAME("LastName"),
    NUMBER("Number");

    override fun toString(): String {
        return status
    }



}