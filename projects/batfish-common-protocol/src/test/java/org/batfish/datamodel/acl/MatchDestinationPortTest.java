package org.batfish.datamodel.acl;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import com.google.common.collect.ImmutableList;
import com.google.common.testing.EqualsTester;
import java.io.IOException;
import org.apache.commons.lang3.SerializationUtils;
import org.batfish.common.util.BatfishObjectMapper;
import org.batfish.datamodel.IntegerSpace;
import org.batfish.datamodel.TraceElement;
import org.junit.Test;

public class MatchDestinationPortTest {
  @Test
  public void testEquals() {
    new EqualsTester()
        .addEqualityGroup(
            new MatchDestinationPort(IntegerSpace.of(1), null),
            new MatchDestinationPort(IntegerSpace.of(1), null))
        .addEqualityGroup(new MatchDestinationPort(IntegerSpace.of(2), null))
        .addEqualityGroup(new MatchDestinationPort(IntegerSpace.of(1), TraceElement.of("test")))
        .testEquals();
  }

  @Test
  public void testSerialization() throws IOException {
    MatchDestinationPort test1 = new MatchDestinationPort(IntegerSpace.of(1), null);
    MatchDestinationPort test2 =
        new MatchDestinationPort(IntegerSpace.of(1), TraceElement.of("test"));
    for (MatchDestinationPort t : ImmutableList.of(test1, test2)) {
      assertThat(BatfishObjectMapper.clone(t, AclLineMatchExpr.class), equalTo(t));
      assertThat(SerializationUtils.clone(t), equalTo(t));
    }
  }
}
