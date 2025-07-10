# jsonlog-parser

This project demonstrates a scalable and low-latency JSON log parser built using Scala and `jsoniter-scala`.

## Features

- Efficient parsing of JSON logs from Elasticsearch
- Extracts `createdDateTime` field and computes:
  - Day of Year
  - Day of Week
  - Hour of Day
- Designed for both:
  - **Batch processing** (model training on millions of entries)
  - **Real-time inference** (latency-optimized)

## File Structure

```
json-parser/
├── build.sbt
├── project/
│   └── build.properties
└── src/
    └── main/
        └── scala/
            └── Main.scala
```

## Prerequisites

- Java 11 or later
- sbt 1.10.0 or later

## How to Run

1. clone this repository:
   ```bash
   git clone my-scala-app.zip
   cd json-parser
   ```

2. Run the project using SBT:
   ```bash
   sbt run
   ```

## Output Example

```
Day of Year: 320
Day of Week: 7
Hour of Day: 17
```

## Scalability & Latency Considerations

- **Scalability**: Designed using stateless pure functions, can be integrated with Akka Streams or Flink for distributed ingestion.
- **Low Latency**: 
  - Uses `jsoniter-scala` which compiles codecs at runtime for high-speed JSON parsing (10–50x faster than traditional parsers).
  - Avoids object-mapping overhead by directly parsing to case classes.
  - Minimal garbage generation = less GC pressure.

## License

MIT
