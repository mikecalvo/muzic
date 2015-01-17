import muzic.LyricExtractor
import org.apache.activemq.ActiveMQConnectionFactory
import org.springframework.jms.connection.SingleConnectionFactory

// Place your Spring DSL code here
beans = {
  lyricExtractor(LyricExtractor) {
    selectorsToSearchIn = ['body', '#start_lyrics']
  }

  jmsConnectionFactory(SingleConnectionFactory) {
    targetConnectionFactory = { ActiveMQConnectionFactory cf ->
      brokerURL = "tcp://localhost:61616"
    }
  }
}
