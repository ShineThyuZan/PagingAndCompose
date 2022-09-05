package klt.mdy.offlinesupportwithpaging.di.network

import javax.inject.Qualifier

object QualifiedAnnotation {

    //Coroutines
    @Retention(AnnotationRetention.RUNTIME)
    @Qualifier
    annotation class Default

    @Retention(AnnotationRetention.RUNTIME)
    @Qualifier
    annotation class Io

    @Retention(AnnotationRetention.RUNTIME)
    @Qualifier
    annotation class Main

    @Retention(AnnotationRetention.RUNTIME)
    @Qualifier
    annotation class ApplicationScope

    @Retention(AnnotationRetention.RUNTIME)
    @Qualifier
    annotation class ViewScope

    @Retention(AnnotationRetention.RUNTIME)
    @Qualifier
    annotation class RepoScope

    //retrofit
    @Retention(AnnotationRetention.BINARY)
    @Qualifier
    annotation class AppRetrofit

    @Retention(AnnotationRetention.BINARY)
    @Qualifier
    annotation class MoviesRetrofit

    //Datastore
    @Retention(AnnotationRetention.BINARY)
    @Qualifier
    annotation class AuthPref

    @Retention(AnnotationRetention.BINARY)
    @Qualifier
    annotation class UserPref

    @Retention(AnnotationRetention.BINARY)
    @Qualifier
    annotation class UserProto

}