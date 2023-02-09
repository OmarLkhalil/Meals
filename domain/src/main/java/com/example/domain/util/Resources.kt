package com.example.domain.util

data class Resources<out T>(
    val status: Status,
    val data: T?=null,
    val message: String?=null
){
    companion object{
        fun <T> success(data:T): Resources<T> {
            return Resources(status = Status.SUCCESS,data,null)
        }
        fun <T> loading(data:T?): Resources<T> {
            return Resources(Status.LOADING,null,null)
        }
        fun <T> failed(data:T?=null,message:String): Resources<T> {
            return Resources(Status.FAIL,null,message)
        }

    }
}