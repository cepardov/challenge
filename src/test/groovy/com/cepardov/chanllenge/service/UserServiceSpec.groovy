package com.cepardov.chanllenge.service

import com.cepardov.chanllenge.dto.UserDTO
import com.cepardov.chanllenge.entity.Phone
import com.cepardov.chanllenge.entity.User
import com.cepardov.chanllenge.repository.UserRepository
import com.cepardov.chanllenge.utils.DTOMapper
import spock.lang.Specification

/**
 * @author cepardov on 01-08-20
 */
class UserServiceSpec extends Specification {

    UserServiceImpl userService
    UserRepository userRepository

    def setup(){
        userRepository = Stub(UserRepository)
        userService = new UserServiceImpl(userRepository: userRepository)
    }

    def "FindAll"() {
        given:
        List<UserDTO> users = []
        userRepository.findAll() >> users
        when:
        def res = userService.findAll()
        then:
        res instanceof List<UserDTO>
    }

    def "Save"() {
        given:
        UserDTO userDTO = userDTOData()
        userRepository.save(_) >> DTOMapper.toEntity(userDTO)
        when:
        def res = userService.save(userDTO)
        then:
        res instanceof UserDTO
    }

    def "FindById"() {
        given:
        Long id = 1
        Optional<User> optionalUser = Optional.of(userEntityData())
        userRepository.findById(_) >> optionalUser

        when:
        def res = userService.findById(id)

        then:
        res instanceof UserDTO
    }

    def "Delete"() {
        given:
        UserDTO userDTO = userDTOData()
        userRepository.delete(_)
        when:
        def res = userService.delete(userDTO)
        then:
        res == null
    }

    def "DeleteById"() {
        when:
        def res = userRepository.deleteById(_)
        then:
        res == null
    }

    User userEntityData(){
        new User(1, "","","", new Date(), new Date(), new Date(), true, [] as Set<Phone>)
    }

    UserDTO userDTOData(){
        UserDTO userDTO = new UserDTO()
        userDTO.id = 1
        userDTO.name = "name"
        userDTO.password = "password"
        userDTO.phones = []
        return userDTO
    }
}
