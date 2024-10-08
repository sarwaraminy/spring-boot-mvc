package com.example.demo.data_repo;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.data.Room;
import com.example.demo.data.RoomRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace=Replace.NONE)
public class RoomRepositoryTest {

	
	@Autowired 
	private RoomRepository roomRepository;
	
	@Test
    public void testFindByName() {
        // Setup data scenario
        Room aNewRoom1 = new Room();
        aNewRoom1.setName("Gulbahar");
        aNewRoom1.setRoomNumber("101");
        aNewRoom1.setBedInfo("Queen size bed");
        roomRepository.save(aNewRoom1);

        Room aNewRoom2 = new Room();
        aNewRoom2.setName("Gulbahar");
        aNewRoom2.setRoomNumber("102");
        aNewRoom2.setBedInfo("King size bed");
        roomRepository.save(aNewRoom2);

        // Find rooms with the same name
        List<Room> foundRooms = roomRepository.findByName("Gulbahar");

        // Assertions
        assertNotNull(foundRooms);
        assertEquals(2, foundRooms.size()); // Ensure all rooms are found
        assertTrue(foundRooms.stream().anyMatch(room -> room.getRoomNumber().equals("101")));
        assertTrue(foundRooms.stream().anyMatch(room -> room.getRoomNumber().equals("102")));
        assertTrue(foundRooms.stream().anyMatch(room -> room.getBedInfo().equals("Queen size bed")));
        assertTrue(foundRooms.stream().anyMatch(room -> room.getBedInfo().equals("King size bed")));
    }

	
}
