package com.asrul.jffplus.core.utils

import com.asrul.jffplus.core.domain.model.Data

interface ItemClickCallbackListener {
    fun onItemClicked(data: Data)
}