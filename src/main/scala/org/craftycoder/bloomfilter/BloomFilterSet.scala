package org.craftycoder.bloomfilter

import scala.annotation.tailrec

class BloomFilterSet(val size: Int = 10000, val hashRounds: Int = 10) {

  require(size > 0, "Size should be higher than 0")
  require(hashRounds > 0, "HashRounds should be higher than 0")

  private val EMPTY_BIT: Byte = 0
  private val FULL_BIT: Byte = 1

  private val byteMap: Array[Byte] = Array.fill[Byte](size)(EMPTY_BIT)

  def put(word: String): Unit = {

    @tailrec
    def put2(hash: String, round: Int): Unit = {
      val (newHash: String, position: Int) = getHashAndPosition(hash)
      byteMap.update(position, FULL_BIT)
      if (round > 0) put2(newHash, round - 1)
    }
    put2(word, hashRounds)
  }
  def contains(word: String): Boolean = {

    @tailrec
    def cointains2(hash: String, round: Int): Boolean = {

      val (newhash: String, position: Int) = getHashAndPosition(hash)

      if (byteMap(position) == EMPTY_BIT) false
      else if (byteMap(position) == FULL_BIT && round == 0) true
      else cointains2(newhash, round - 1)

    }

    cointains2(word, hashRounds)

  }

  private def generateHash(word: String): String = {
    import java.security.MessageDigest
    val messageDigest = MessageDigest.getInstance("SHA-512")
    messageDigest.update(word.getBytes)
    val stringBuilder = new StringBuilder
    for (aByte <- messageDigest.digest) {
      stringBuilder.append(
        Integer.toString((aByte & 0xff) + 0x100, 16).substring(1))
    }
    stringBuilder.toString
  }

  private def getHashAndPosition(word: String) = {
    val hash = generateHash(word)
    val position = Math.abs(hash.hashCode) % size
    (hash, position)
  }
}
