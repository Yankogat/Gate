package ru.perm.school9.gate.model

//TODO
// rewrite according to new ideas
open class TestGroup (
        open var tests: List<Test>?,
        open var testGroups: List<TestGroup>?,
        open var checkerId: String?,
        open var conjunctive: Boolean?,
        open var value: Int?
)
