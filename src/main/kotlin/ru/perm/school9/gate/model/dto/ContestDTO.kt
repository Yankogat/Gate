package ru.perm.school9.gate.model.dto

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document


/** ContestDataTransferObject
 *  Represents the way how contests are stored in the database
 * */
@Document(collection = "Contests")
data class ContestDTO (
        @Id
        var id: String,

        var problemsIds: List<String>,
        var userIds: List<String>
) : DTO
