# simple-crawler

## Instructions
1. Clone the repo
2. `./gradlew build` 
3. `java -jar ./build/libs/simple-crawler-1.0-SNAPSHOT.jar [startUrl] [domain]` 
- Where `startUrl` is any page within `domain`
- And a properly formed `domain` is of the form `mydomain.tld`

## Explanation
- The program takes two separate arguments for the `startUrl` and the `domain`
-- It was difficult to reliably extract the domain using regex from an arbitrary `startUrl`
- Jsoup library was used to GET and parse HTML, instead of using HttpUrlConnection and XML parsing to save time
-- No need to reinvent the wheel here
- A `HashSet` of `seen` URLs is built up over the course of crawling
-- This means that for any given page, it will not show children that have been previously seen
-- This gives a more concise representation of the sitemap, from a human point of view, though less technically accurate
- It was clear that some form of tree structure would be required to represent the data
- BFS is used to crawl sites, the assumption being that most sites do not have significant link depth

## Given more time we could produce
- A robust test suite could be put together
-- Code would have to be re-written to be more easily testable
- An appealing UI to present the sitemap possibly using a JS framework
