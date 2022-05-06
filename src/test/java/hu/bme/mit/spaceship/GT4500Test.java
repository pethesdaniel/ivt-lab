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

    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    verify(mockStorePrimary, times(1)).fire(1);
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
  }

}
