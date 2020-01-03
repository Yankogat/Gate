package ru.perm.school9.gate.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.Field

@Document(collection = "Problems")
data class Problem (
        @Id
        var id: String?,
        var name: String?,
        var wording: String?,
        var tests: List<Test>?,
        var testGroups: List<TestGroup>?,
        var checkerId: String?,
        var conjuctive: Boolean?,
        var value: Int?
)