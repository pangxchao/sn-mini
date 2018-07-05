package sn.mini.kotlin.jdbc.sql

import java.util.*

abstract class Sql protected constructor() {
    private val content = StringBuilder()
    private val params = ArrayList<Any?>()

    open fun contentToString() = this.content.toString()
    fun paramsToArray(): Array<out Any> = this.params.toArray()
    fun paramsToStringArray(): Array<out String> = this.params.toArray(arrayOf<String>())

    abstract class Build(protected open val sql: Sql) {
        open fun build(): Sql {
            return this.sql
        }

        open fun append(sql: String): Build {
            this.sql.content.append(sql)
            return this
        }

        open fun addParams(vararg params: Any?): Build {
            this.sql.params.addAll(params)
            return this
        }

    }
}
