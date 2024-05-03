
const stateCity = {
  "Alabama": ["Auburn", "Birmingham", "Dothan", "Florence / Muscle Shoals", "Gadsden-Anniston", "Huntsville / Decatur", "Mobile", "Montgomery", "Tuscaloosa"],
  "Alaska": ["Anchorage / Mat-Su", "Fairbanks", "Kenai Peninsula", "Southeast Alaska"],
  "Arizona": ["Flagstaff / Sedona", "Mohave County", "Phoenix", "Prescott", "Show Low", "Sierra Vista", "Tucson", "Yuma"],
  "Arkansas": ["Fayetteville", "Fort Smith", "Jonesboro", "Little Rock", "Texarkana"],


    // TODO verfy these match my 'craigslist list'
  "California": ["Bakersfield", "Chico", "Fresno / Madera", "Gold Country", "Hanford-Corcoran", "Humboldt County", "Imperial County", "Inland Empire", "Los Angeles", "Mendocino County", "Merced", "Modesto", "Monterey Bay", "Orange County", "Palm Springs", "Redding", "Sacramento", "San Diego", "San Francisco Bay Area", "San Luis Obispo", "Santa Barbara", "Santa Maria", "Siskiyou County", "Stockton", "Susanville", "Ventura County", "Visalia-Tulare", "Yuba-Sutter"],
  "Colorado": ["Boulder", "Colorado Springs", "Denver", "Eastern CO", "Fort Collins / North CO", "High Rockies", "Pueblo", "Western Slope"],
  "Connecticut": ["Eastern CT", "Hartford", "New Haven", "Northwest CT"],
  "Delaware": ["Delaware"],
  "Florida": ["Daytona Beach", "Florida Keys", "Ft Myers / SW Florida", "Gainesville", "Heartland Florida", "Jacksonville", "Lakeland", "North Central FL", "Ocala", "Okaloosa / Walton", "Orlando", "Panama City", "Pensacola", "Sarasota-Bradenton", "South Florida", "Space Coast", "St Augustine", "Tallahassee", "Tampa Bay Area", "Treasure Coast"],
  "Georgia": ["Albany", "Athens", "Atlanta", "Augusta", "Brunswick", "Columbus", "Macon / Warner Robins", "Northwest GA", "Savannah / Hinesville", "Statesboro", "Valdosta"],
  "Hawaii": ["Big Island", "Honolulu", "Kauai", "Maui"],
  "Idaho": ["Boise", "East Idaho", "Lewiston / Clarkston", "Twin Falls"],
  "Illinois": ["Bloomington-Normal", "Champaign Urbana", "Chicago", "Decatur", "La Salle CO", "Mattoon-Charleston", "Peoria", "Rockford", "Southern Illinois", "Springfield", "Western IL"],
  "Indiana": ["Bloomington", "Evansville", "Fort Wayne", "Indianapolis", "Kokomo", "Lafayette / West Lafayette", "Muncie / Anderson", "Richmond", "South Bend / Michiana", "Terre Haute"],
  "Iowa": ["Ames", "Cedar Rapids", "Des Moines", "Dubuque", "Fort Dodge", "Iowa City", "Mason City", "Quad Cities", "Sioux City", "Southeast IA", "Waterloo / Cedar Falls"],
  "Kansas": ["Lawrence", "Manhattan", "Northwest KS", "Salina", "Southeast KS", "Southwest KS", "Topeka", "Wichita"],
  "Kentucky": ["Bowling Green", "Eastern Kentucky", "Lexington", "Louisville", "Owensboro", "Western KY"],
  "Louisiana": ["Baton Rouge", "Central Louisiana", "Houma", "Lafayette", "Lake Charles", "Monroe", "New Orleans", "Shreveport"],
  "Maine": ["Maine"],
  "Maryland": ["Annapolis", "Baltimore", "Eastern Shore", "Frederick", "Southern Maryland", "Western Maryland"],
  "Massachusetts": ["Boston", "Cape Cod / Islands", "South Coast", "Western Massachusetts", "Worcester / Central MA"],
  "Michigan": ["Ann Arbor", "Battle Creek", "Central Michigan", "Detroit Metro", "Flint", "Grand Rapids", "Holland", "Jackson", "Kalamazoo", "Lansing", "Monroe", "Muskegon", "Northern Michigan", "Port Huron", "Saginaw-Midland-Baycity", "Southwest Michigan", "The Thumb", "Upper Peninsula"],
  "Minnesota": ["Bemidji", "Brainerd", "Duluth / Superior", "Mankato", "Minneapolis / St Paul", "Rochester", "Southwest MN", "St Cloud"],
  "Mississippi": ["Gulfport / Biloxi", "Hattiesburg", "Jackson", "Meridian", "North Mississippi"],
  "Missouri": ["Columbia / Jeff City", "Joplin", "Kansas City", "Kirksville", "Lake of the Ozarks", "Southeast Missouri", "Springfield", "St Joseph", "St Louis"],
  "Montana": ["Billings", "Bozeman", "Butte", "Great Falls", "Helena", "Kalispell", "Missoula", "Eastern Montana"],
  "Nebraska": ["Grand Island", "Lincoln", "North Platte", "Omaha / Council Bluffs", "Scottsbluff / Panhandle"],
  "Nevada": ["Elko", "Las Vegas", "Reno / Tahoe"],
  "New Hampshire": ["New Hampshire"],
  "New Jersey": ["Central NJ", "Jersey Shore", "North Jersey", "South Jersey"],
  "New Mexico": ["Albuquerque", "Clovis / Portales", "Las Cruces", "Roswell / Carlsbad", "Santa Fe / Taos"],
  "New York": ["Albany", "Binghamton", "Buffalo", "Catskills", "Chautauqua", "Elmira-Corning", "Finger Lakes", "Glens Falls", "Hudson Valley", "Ithaca", "Long Island", "New York City", "Oneonta", "Plattsburgh-Adirondacks", "Potsdam-Canton-Massena", "Rochester", "Syracuse", "Twin Tiers NY/PA", "Utica-Rome-Oneida", "Watertown"],
  "North Carolina": ["Asheville", "Boone", "Charlotte", "Eastern NC", "Fayetteville", "Greensboro", "Hickory / Lenoir", "Jacksonville", "Outer Banks", "Raleigh / Durham / CH", "Wilmington", "Winston-Salem"],
  "North Dakota": ["Bismarck", "Fargo / Moorhead", "Grand Forks", "North Dakota"],
  "Ohio": ["Akron / Canton", "Ashtabula", "Athens", "Chillicothe", "Cincinnati", "Cleveland", "Columbus", "Dayton / Springfield", "Lima / Findlay", "Mansfield", "Sandusky", "Toledo", "Tuscarawas CO", "Youngstown", "Zanesville / Cambridge"],
  "Oklahoma": ["Lawton", "Northwest OK", "Oklahoma City", "Stillwater", "Tulsa"],
  "Oregon": ["Bend", "Corvallis/Albany", "East Oregon", "Eugene", "Klamath Falls", "Medford-Ashland", "Oregon Coast", "Portland", "Roseburg", "Salem"],
  "Pennsylvania": ["Allentown", "Altoona-Johnstown", "Cumberland Valley", "Erie", "Harrisburg", "Lancaster", "Lehigh Valley", "Meadeville", "Philadelphia", "Pittsburgh", "Poconos", "Reading", "Scranton / Wilkes-Barre", "State College", "Williamsport", "York"],
  "Rhode Island": ["Rhode Island"],
  "South Carolina": ["Charleston", "Columbia", "Florence", "Greenville / Upstate", "Hilton Head", "Myrtle Beach"],
  "South Dakota": ["Northeast SD", "Pierre / Central SD", "Rapid City / West SD", "Sioux Falls / SE SD", "South Dakota"],
  "Tennessee": ["Chattanooga", "Clarksville", "Cookeville", "Knoxville", "Memphis", "Nashville", "Tri-Cities"],
  "Texas": ["Abilene", "Amarillo", "Austin", "Beaumont / Port Arthur", "Brownsville", "College Station", "Corpus Christi", "Dallas / Fort Worth", "Deep East Texas", "Del Rio / Eagle Pass", "El Paso", "Galveston", "Houston", "Killeen / Temple / Ft Hood", "Laredo", "Lubbock", "McAllen / Edinburg", "Odessa / Midland", "San Angelo", "San Antonio", "San Marcos", "Southwest TX", "Texoma", "Tyler / East TX", "Victoria", "Waco", "Wichita Falls"],
  "Utah": ["Logan", "Ogden-Clearfield", "Provo / Orem", "Salt Lake City", "St George"],
  "Vermont": ["Vermont"],
  "Virginia": ["Charlottesville", "Danville", "Fredericksburg", "Hampton Roads", "Harrisonburg", "Lynchburg", "New River Valley", "Richmond", "Roanoke", "Southwest VA", "Winchester"],
  "Washington": ["Bellingham", "Kennewick-Pasco-Richland", "Moses Lake", "Olympic Peninsula", "Pullman / Moscow", "Seattle-Tacoma", "Skagit / Island / SJI", "Spokane / Coeur d'Alene", "Wenatchee", "Yakima"],
  "West Virginia": ["Charleston", "Eastern Panhandle", "Huntington-Ashland", "Morgantown", "Northern Panhandle", "Parkersburg-Marietta", "Southern WV", "West Virginia (old)"],
  "Wisconsin": ["Appleton-Oshkosh-FDL", "Eau Claire", "Green Bay", "Janesville", "Kenosha-Racine", "La Crosse", "Madison", "Milwaukee", "Northern WI", "Sheboygan", "Wausau"],
  "Wyoming": ["Wyoming"]
}


function stateSelect(state){
    var cities = stateCity[state];
    console.log(cities);

}