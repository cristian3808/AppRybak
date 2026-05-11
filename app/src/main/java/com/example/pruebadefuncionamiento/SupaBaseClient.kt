package com.example.pruebadefuncionamiento

import io.github.jan.supabase.auth.Auth
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.postgrest.Postgrest

object SupaBaseClient {

    val client = createSupabaseClient(

        supabaseUrl = "https://saoowgectkkhlotmfwlr.supabase.co",

        supabaseKey = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6InNhb293Z2VjdGtraGxvdG1md2xyIiwicm9sZSI6ImFub24iLCJpYXQiOjE3NzgzOTM2ODEsImV4cCI6MjA5Mzk2OTY4MX0.vnEaVRyMWGGsXjMyVMIU1kCyuCz9KT_yFoDPSW0X5W0"

    ) {

        install(Postgrest)
        install(Auth)
    }
}