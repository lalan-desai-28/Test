package com.lalan.test.model.dashboard

import com.google.gson.annotations.SerializedName


data class DashboardDataResponse(

    @SerializedName("data") var data: ArrayList<Data> = arrayListOf(),
    @SerializedName("meta") var meta: Meta? = Meta()

)