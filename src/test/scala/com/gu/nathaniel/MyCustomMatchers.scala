package com.gu.nathaniel

import org.scalatest._
import matchers._

import java.io.File

trait MyCustomMatchers {

  class FileExtensionMatcher(expectedExtensiion: String) extends Matcher[File] {
    override def apply(file: File): MatchResult = {
      MatchResult(
        file.getName.endsWith(expectedExtensiion),
        s"""File ${file.getName} did not end with ${expectedExtensiion}""",
        s"""File ${file.getName} ended with ${expectedExtensiion}"""
      )
    }
  }
  def endWithExtension(expectedExtension: String) = new FileExtensionMatcher(expectedExtension)

}

object MyCustomMatchers extends MyCustomMatchers