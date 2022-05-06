package hu.bme.mit.spaceship;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class GT4500Test {
  private TorpedoStore mockStorePrimary = mock(TorpedoStore.class);
  private TorpedoStore mockStoreSecondary = mock(TorpedoStore.class);

  private GT4500 ship;

  @BeforeEach
  public void init(){
    this.ship = new GT4500(mockStorePrimary, mockStoreSecondary);
  }

  @Test
  public void fireTorpedo_Single_Success(){
    // Arrange
    when(mockStorePrimary.fire(1)).thenReturn(true);
    when(mockStoreSecondary.fire(1)).thenReturn(true);

    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);
    boolean result2 = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    verify(mockStorePrimary, times(1)).fire(1);
    verify(mockStoreSecondary, times(1)).fire(1);
  }

  @Test
  public void fireTorpedo_Single_OnlySingle(){
    // Arrange
    when(mockStorePrimary.fire(1)).thenReturn(true);
    when(mockStoreSecondary.fire(1)).thenReturn(true);

    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    verify(mockStorePrimary, times(1)).fire(1);
    verify(mockStoreSecondary, times(0)).fire(1);
  }
  @Test
  public void fireTorpedo_Single_Alternate(){
    // Arrange
    when(mockStorePrimary.fire(1)).thenReturn(true);
    when(mockStoreSecondary.fire(1)).thenReturn(true);

    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    verify(mockStorePrimary, times(1)).fire(1);

    // Act
    boolean result2 = ship.fireTorpedo(FiringMode.SINGLE);

    //Assert
    verify(mockStoreSecondary, times(1)).fire(1);

    // Act
    boolean result3 = ship.fireTorpedo(FiringMode.SINGLE);

    //Assert
    verify(mockStorePrimary, times(2)).fire(1);
  }

  @Test
  public void fireTorpedo_Single_Alternate_SecondOneTorpedo(){
    // Arrange
    when(mockStorePrimary.fire(1)).thenReturn(true);
    when(mockStoreSecondary.isEmpty()).thenReturn(false).thenReturn(true);
    when(mockStoreSecondary.fire(1)).thenReturn(true).thenReturn(false);

    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);
    boolean result2 = ship.fireTorpedo(FiringMode.SINGLE);
    boolean result3 = ship.fireTorpedo(FiringMode.SINGLE);
    boolean result4 = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    verify(mockStorePrimary, times(3)).fire(1);
    verify(mockStoreSecondary, times(1)).fire(1);
  }

  @Test
  public void fireTorpedo_Single_OneEmpty(){
    // Arrange
    when(mockStorePrimary.fire(1)).thenReturn(false);
    when(mockStoreSecondary.fire(1)).thenReturn(true);
    when(mockStorePrimary.isEmpty()).thenReturn(true);
    when(mockStoreSecondary.isEmpty()).thenReturn(false);

    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    verify(mockStorePrimary, times(1)).isEmpty();
    verify(mockStorePrimary, times(0)).fire(1);
    verify(mockStoreSecondary, times(1)).isEmpty();
    verify(mockStoreSecondary, times(1)).fire(1);
  }

  @Test
  public void fireTorpedo_All_Success(){
    // Arrange
    when(mockStorePrimary.fire(1)).thenReturn(true);
    when(mockStoreSecondary.fire(1)).thenReturn(true);


    // Act
    boolean result = ship.fireTorpedo(FiringMode.ALL);

    // Assert
    verify(mockStorePrimary, times(1)).fire(1);
    verify(mockStoreSecondary, times(1)).fire(1);
    assertEquals(true, result);
  }


  @Test
  public void fireTorpedo_All_BothEmpty(){
    // Arrange
    when(mockStorePrimary.fire(1)).thenReturn(false);
    when(mockStoreSecondary.fire(1)).thenReturn(false);
    when(mockStorePrimary.isEmpty()).thenReturn(true);
    when(mockStoreSecondary.isEmpty()).thenReturn(true);

    // Act
    boolean result = ship.fireTorpedo(FiringMode.ALL);

    // Assert
    verify(mockStorePrimary, times(1)).isEmpty();
    verify(mockStorePrimary, times(0)).fire(1);
    verify(mockStoreSecondary, times(1)).isEmpty();
    verify(mockStoreSecondary, times(0)).fire(1);
    assertEquals(false, result);
  }

  @Test
  public void fireTorpedo_All_PrimaryEmpty(){
    // Arrange
    when(mockStorePrimary.fire(1)).thenReturn(false);
    when(mockStoreSecondary.fire(1)).thenReturn(true);
    when(mockStorePrimary.isEmpty()).thenReturn(true);
    when(mockStoreSecondary.isEmpty()).thenReturn(false);

    // Act
    boolean result = ship.fireTorpedo(FiringMode.ALL);
    boolean result2 = ship.fireTorpedo(FiringMode.ALL);

    // Assert
    verify(mockStorePrimary, times(2)).isEmpty();
    verify(mockStorePrimary, times(0)).fire(1);
    verify(mockStoreSecondary, times(2)).fire(1);
    assertEquals(true, result);
    assertEquals(true, result2);
  }

}
