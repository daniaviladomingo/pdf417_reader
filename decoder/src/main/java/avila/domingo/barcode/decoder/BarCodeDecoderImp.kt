package avila.domingo.barcode.decoder

import avila.domingo.barcode.decoder.mapper.BinaryBitmapMapper
import avila.domingo.barcode.decoder.mapper.ResultMapper
import avila.domingo.barcode.domain.IBarCodeDecoder
import avila.domingo.barcode.domain.model.PreviewImage
import com.google.zxing.DecodeHintType
import com.google.zxing.Reader
import com.google.zxing.ReaderException
import io.reactivex.Single

class BarCodeDecoderImp(
    private val reader: Reader,
    private val hints: Map<DecodeHintType, *>,
    private val resultMapper: ResultMapper,
    private val binaryBitmapMapper: BinaryBitmapMapper
) : IBarCodeDecoder {
    override fun decode(image: PreviewImage): Single<String> = Single.create {
        try {
            it.onSuccess(resultMapper.map(reader.decode(binaryBitmapMapper.map(image), hints)))
        } catch (e: ReaderException) {
        }
    }
}