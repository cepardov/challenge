package com.cepardov.challenge.service

import spock.lang.Specification

/**
 * @author cepardov on 02-08-20
 */
class PasswordServiceSpec extends Specification {

    PasswordServiceImpl passwordService

    def setup() {
        passwordService = new PasswordServiceImpl()
    }

    def "Hash"() {
        when:
        def res = passwordService.hash("123")
        then:
        res != null
    }

    def "VerifyHash"() {
        given:
        def pass = "123"
        def hash = passwordService.hash(pass)
        when:
        def res = passwordService.verifyHash(pass, hash)
        then:
        res
    }
}
