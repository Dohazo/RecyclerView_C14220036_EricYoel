package eric.c14220036.recyclerview_c14220036_ericyoel

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class wayang(
    var foto : String,
    var nama : String,
    var karakter : String,
    var deskripsi : String
) : Parcelable
