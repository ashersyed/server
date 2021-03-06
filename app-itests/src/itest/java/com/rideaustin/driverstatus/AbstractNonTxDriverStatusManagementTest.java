package com.rideaustin.driverstatus;

import javax.inject.Inject;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.google.maps.model.LatLng;
import com.rideaustin.config.AppConfig;
import com.rideaustin.config.RAApplicationInitializer;
import com.rideaustin.config.WebConfig;
import com.rideaustin.model.Area;
import com.rideaustin.repo.dsl.ActiveDriverDslRepository;
import com.rideaustin.service.areaqueue.AreaQueueUpdateService;
import com.rideaustin.service.config.RideDispatchServiceConfig;
import com.rideaustin.test.AbstractNonTxTests;
import com.rideaustin.test.actions.DriverAction;
import com.rideaustin.test.actions.RiderAction;
import com.rideaustin.test.common.Sleeper;
import com.rideaustin.test.config.FixtureConfig;
import com.rideaustin.test.config.TestActionsConfig;
import com.rideaustin.test.config.TestSetupConfig;
import com.rideaustin.test.setup.SetupAction;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfig.class, WebConfig.class, FixtureConfig.class, TestActionsConfig.class, TestSetupConfig.class}, initializers = RAApplicationInitializer.class)
@WebAppConfiguration
public abstract class AbstractNonTxDriverStatusManagementTest<T extends SetupAction<T>> extends AbstractNonTxTests<T> {

  @Inject
  protected DriverAction driverAction;

  @Inject
  protected RiderAction riderAction;

  @Inject
  protected RideDispatchServiceConfig rideDispatchServiceConfig;

  @Inject
  protected AreaQueueUpdateService queueService;

  @Inject
  protected Sleeper sleeper;

  @Inject
  protected ActiveDriverDslRepository activeDriverDslRepository;

  protected LatLng austinCenter;

  protected LatLng airportLocation;

  protected LatLng outsideAirportLocation;

  protected Area airport;

  protected int declinedRidesLimit;

  @Override
  @Before
  public void setUp() throws Exception {
    super.setUp();
    airport = locationProvider.getAirport();
    austinCenter = locationProvider.getCenter();
    airportLocation = locationProvider.getAirportLocation();
    outsideAirportLocation = locationProvider.getOutsideAirportLocation();
    declinedRidesLimit = rideDispatchServiceConfig.getDriverMaxDeclinedRequests();
  }
}
