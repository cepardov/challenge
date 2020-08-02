package com.cepardov.challenge.controller


import com.cepardov.challenge.dto.PhoneDTO
import com.cepardov.challenge.dto.UserDTO
import com.cepardov.challenge.service.UserService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import spock.lang.Specification
/**
 * @author cepardov on 01-08-20
 */
class UserControllerSpec extends Specification {

    UserController userController
    UserService userService = Stub(UserService)

    def setup(){
        userController = new UserController(userService)
    }

    def "get all users"() {
        given:
        List<UserDTO> users = new ArrayList<>()
        50.times {
            users.add(new UserDTO(it, "","","", new Date(), new Date(), new Date(), true, [] as Set<PhoneDTO>))
        }
        userService.findAll() >> users
        when:
        def res = userController.list()
        List<UserDTO> usersList = res.getBody() as List<UserDTO>
        then:
        res instanceof ResponseEntity
        usersList.size() == 50
    }

    def "get User details for id"() {
        given:
        UserDTO user = new UserDTO(1, "","","", new Date(), new Date(), new Date(), true, [] as Set<PhoneDTO>)
        userService.findById(_ as Long) >> user
        when:
        def res = userController.getUserById(1)
        def userFound = res.getBody() as UserDTO
        then:
        res instanceof ResponseEntity
        userFound instanceof UserDTO
        userFound.id == 1
    }

    def "Save user correct fields"() {
        given:
        UserDTO userFields = new UserDTO(0, "Juan Rodriguez","Hunter22","juan@rodriguez.com", null, null, null, true, [] as Set<PhoneDTO>)
        UserDTO userSaved = new UserDTO(1, "Juan Rodriguez","Hunter22","juan@rodriguez.com", new Date(), null, null, true, [] as Set<PhoneDTO>)
        userService.save(_ as UserDTO) >> userSaved
        when:
        def res = userController.save(userFields)
        def body = res.getBody()
        then:
        res instanceof ResponseEntity
        res.statusCode == HttpStatus.CREATED
        body instanceof UserDTO
    }

    def "Update user correct fields"() {
        given:
        UserDTO userFields = new UserDTO(1, "Juan Rodriguez","Hunter22","juan@rodriguez.com", new Date(), null, null, true, [] as Set<PhoneDTO>)
        UserDTO userUpdated = new UserDTO(1, "Juan Rodriguez","Hunter22","juan@rodriguez.com", new Date(), new Date(), null, true, [] as Set<PhoneDTO>)
        userService.save(_ as UserDTO) >> userUpdated
        when:
        def res = userController.update(userFields)
        def body = res.getBody()
        then:
        res instanceof ResponseEntity
        res.statusCode == HttpStatus.OK
        body instanceof UserDTO
    }

    def "Delete user use DTO"() {
        given:
        UserDTO user = new UserDTO(1, "Juan Rodriguez","Hunter22","juan@rodriguez.com", new Date(), new Date(), null, true, [] as Set<PhoneDTO>)
        userService.delete(_ as UserDTO) >> null
        when:
        def res = userController.delete(user)
        then:
        res instanceof ResponseEntity
        res.statusCode == HttpStatus.OK
    }

    def "Delete user use ID"() {
        given:
        userService.deleteById(_ as Long) >> null
        when:
        def res = userController.deleteById(1)
        then:
        res instanceof ResponseEntity
        res.statusCode == HttpStatus.OK
    }
}
