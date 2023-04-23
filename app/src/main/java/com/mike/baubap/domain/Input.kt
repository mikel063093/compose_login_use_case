package com.mike.baubap.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Input(
    val value: String? = null
) : Parcelable

internal fun Input.isInitialValue() = value == null