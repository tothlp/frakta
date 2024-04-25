package hu.tothlp.hu.tothlp.frakta.app.core.interaction.encryption

import javax.crypto.SecretKey

interface EncryptionService {
	fun generateAESKey(keySize: Int): SecretKey

	fun aesEncrypt(data: ByteArray, secretKey: SecretKey): ByteArray

	fun aesDecrypt(encryptedData: ByteArray, secretKey: SecretKey): ByteArray

	fun retrieveKey(): SecretKey
}