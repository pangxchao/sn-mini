@file:Suppress("unused", "FunctionName", "RedundantLambdaArrow")
@file:JvmName("ReplaceSqlKt")

package com.mini.core.jdbc.builder


fun replaceInfo(table: String, init: ReplaceSql.() -> Unit): ReplaceSql {
    return object : ReplaceSql() {}.replaceInto(table).apply(init)
}

