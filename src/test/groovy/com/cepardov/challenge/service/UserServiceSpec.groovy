package com.cepardov.challenge.service

import com.cepardov.challenge.dto.UserDTO
import com.cepardov.challenge.entity.Phone
import com.cepardov.challenge.entity.User
import com.cepardov.challenge.repository.UserRepository
import spock.lang.Specification

/**
 * @author cepardov on 01-08-20
 */
class UserServiceSpec extends Specification {

    UserServiceImpl userService
    UserRepository userRepository = Stub(UserRepository)
    PasswordService passwordService = Stub(PasswordService)

    def setup(){
        userService = new UserServiceImpl(userRepository, passwordService)
    }

    def "FindAll"() {
        given:
        List<User> users = userListData()
        userRepository.findAll() >> users
        when:
        def res = userService.findAll()
        then:
        res instanceof List<UserDTO>
        res.size() == 3
    }

    def "Save"() {
        given:
        UserDTO userDTO = userDTOData()
        userRepository.save(_) >> userData()
        when:
        def res = userService.save(userDTO)
        then:
        res instanceof UserDTO
    }

    def "Update"() {
        given:
        UserDTO userDTO = userDTOData()
        userRepository.save(_) >> userData()
        when:
        def res = userService.update(userDTO)
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
         return new User(1, "","","","", new Date(), new Date(), new Date(), true, [] as Set<Phone>)
    }

    UserDTO userDTOData(){
        UserDTO userDTO = new UserDTO()
        userDTO.id = 1
        userDTO.name = "name"
        userDTO.password = "password"
        userDTO.phones = []
        return userDTO
    }

    User userData(){
        new User(1, "","","", "", new Date(), new Date(), new Date(), true, [] as Set<Phone>)
    }

    List<User> userListData(){
        List<User> list = new ArrayList<>()
        3.times {
            list.add(new User(it, "","","","", new Date(), new Date(), new Date(), true, [] as Set<Phone>))
        }
        return list
    }
}
