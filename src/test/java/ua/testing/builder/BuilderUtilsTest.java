package ua.testing.builder;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.runner.RunWith;
import ua.testing.builder.domain.*;

import javax.inject.Inject;

import static org.junit.Assert.assertEquals;

/**
 * @author timofey.sukhachev
 */
@RunWith(Arquillian.class)
public class BuilderUtilsTest {

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class, "test.jar")
                .addPackage("ua.testing.builder")
                .addPackage("ua.testing.builder.domain")
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @Inject
    private CreditCardBuilder creditCardBuilder;

    @Inject
    private PaymentBuilder paymentBuilder;

    @Test
    public void testFromEnumToBuilder() throws Exception {
        BuilderUtils.fromEnumToBuilder(creditCardBuilder, Card.VISA);
        CreditCard build = creditCardBuilder.build();
        assertEquals(Card.VISA.getNumber(), build.getNumber());
        assertEquals(Card.VISA.getExpDate(), build.getExpDate());
    }

    @Test
    public void testFromBuilderToObject() throws Exception {
        String expectedNumber = "123";
        CreditCard build = creditCardBuilder
                .setNumber(expectedNumber)
                .build(Card.VISA);
        assertEquals(expectedNumber, build.getNumber());
        assertEquals(Card.VISA.getExpDate(), build.getExpDate());
    }

    @Test
    public void testCopyProperties() throws Exception {
//        Payment payment = paymentBuilder.build(PaymentEnum.PURCHASE_OF_BOOK);
    }
}