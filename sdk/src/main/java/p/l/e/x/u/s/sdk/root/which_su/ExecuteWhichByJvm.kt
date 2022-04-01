package p.l.e.x.u.s.sdk.root.which_su

import p.l.e.x.u.s.sdk.usecases.root.which_su.ICanExecuteWhich
import java.io.ByteArrayOutputStream
import java.io.IOException

class ExecuteWhichByJvm : ICanExecuteWhich {

    override fun executeWhich(parameter: String): String =
        Runtime
            .getRuntime()
            .exec(WHICH + parameter.trim())
            ?.run {
                // auto close Closeable
                inputStream?.use { response ->
                    try {
                        // auto close Closeable
                        ByteArrayOutputStream().use { byteStream ->
                            val buffer = ByteArray(BUFFER_SIZE)
                            var bytesRead = 0
                            while (bytesRead != ZERO_BYTES_READ) {
                                byteStream.write(buffer, OFFSET, bytesRead)
                                // bytesRead = ZERO_BYTES_READ if no bytes read (-1)
                                bytesRead = response.read(buffer)
                            }
                            val responseString = byteStream.toString(CHARSET)
                            if (responseString.isNotBlank()) {
                                /*log(
                                    "ExecuteWhichByJvm with " +
                                            "parameter = $parameter, " +
                                            "response = $responseString"
                                )*/
                            }
                            responseString
                        }
                    } catch (e: IOException) {
                        //log("ExecuteWhichByJvm error: $e")
                        //e.printStackTrace()
                        EMPTY_STRING
                    }
                } ?: EMPTY_STRING
            }
            ?: EMPTY_STRING

    companion object {
        private const val WHICH = "which "
        private const val EMPTY_STRING = ""
        private const val CHARSET = "UTF-8"
        private const val BUFFER_SIZE = 1024
        private const val ZERO_BYTES_READ = -1
        private const val OFFSET = 0
    }
}