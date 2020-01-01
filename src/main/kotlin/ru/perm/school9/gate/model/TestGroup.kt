package ru.perm.school9.gate.model

open class TestGroup (
        open var tests: List<Test>?,
        open var testGroups: List<TestGroup>?,
        open var checkerId: String?,
        //TODO
        // decide on better name
        open var conjuctive: Boolean?,
        open var value: Int?
)
