package ru.perm.school9.gate.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "Problems")
data class Problem (
        @Id
        var id: String?,
        var name: String?,
        var wording: String?,
        override var tests: List<Test>?,
        override var testGroups: List<TestGroup>?,
        override var checkerId: String?,
        override var conjuctive: Boolean?,
        override var value: Int?
) : TestGroup(tests, testGroups, checkerId, conjuctive, value)