package hu.tothlp.hu.tothlp.frakta.app.core.interaction.encryption

import com.github.ajalt.mordant.terminal.Terminal
import hu.tothlp.hu.tothlp.frakta.app.core.di.BeanRegistry
import java.util.*
import javax.crypto.Cipher
import javax.crypto.KeyGenerator
import javax.crypto.SecretKey
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec

class AesEncryptionService(
	private val terminal: Terminal = BeanRegistry.getBean<Terminal>(),
) : EncryptionService {
	private val keyEnvVarName = "FRAKTA_KEY"
	private val cipherType = "AES/CBC/PKCS5Padding"
	private val defaultKeySize = 256

	override fun generateAESKey(keySize: Int): SecretKey {
		val keyGenerator = KeyGenerator.getInstance("AES")
		keyGenerator.init(keySize)
		return keyGenerator.generateKey()
	}

	override fun aesEncrypt(data: ByteArray, secretKey: SecretKey): ByteArray {
		val cipher = Cipher.getInstance(cipherType)
		val ivParameterSpec = IvParameterSpec(ByteArray(16)) // Use a secure IV in production
		cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivParameterSpec)
		return cipher.doFinal(data)
	}

	override fun aesDecrypt(encryptedData: ByteArray, secretKey: SecretKey): ByteArray {
		val cipher = Cipher.getInstance(cipherType)
		val ivParameterSpec = IvParameterSpec(ByteArray(16)) // Use the same IV as used in encryption
		cipher.init(Cipher.DECRYPT_MODE, secretKey, ivParameterSpec)
		return cipher.doFinal(encryptedData)
	}

	override fun retrieveKey(): SecretKey =
			System.getenv(keyEnvVarName)?.let { stringToSecretKey(it) } ?: saveNewKey()

	private fun saveNewKey(): SecretKey {
		val key = generateAESKey(defaultKeySize)
		val encodedKey = secretKeyToString(key)
		System.setProperty(keyEnvVarName, encodedKey)
		terminal.warning("No encryption key found. A new key has been generated and saved temporarily. Please save it to $keyEnvVarName environment variable.")
		terminal.info("Key: $encodedKey")
		return key
	}

	private fun secretKeyToString(key: SecretKey): String {
		return Base64.getEncoder().encodeToString(key.encoded)
	}

	private fun stringToSecretKey(encodedKey: String): SecretKey {
		val decodedKey = Base64.getDecoder().decode(encodedKey)
		return SecretKeySpec(decodedKey, 0, decodedKey.size, "AES")
	}


}