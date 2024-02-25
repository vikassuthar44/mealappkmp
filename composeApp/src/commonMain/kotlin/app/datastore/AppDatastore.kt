package app.datastore

interface AppDatastore {

    suspend fun setValue(
        key: String,
        value: String
    )

    suspend fun getValue(
        key: String
    ): String?
}