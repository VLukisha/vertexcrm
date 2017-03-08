package ua.com.vertex.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("test")
public class DiscountDaoTest {

        @Autowired
        private DiscountDaoInf underTest;

        @Test
        public void getDiscount() throws Exception {
            assertNotNull(underTest.getDiscount(5));
        }

        @Test
        public void updateDiscountAmount(){
            assertNotEquals(0, underTest.updateDiscount(3,10000));
        }
    }


