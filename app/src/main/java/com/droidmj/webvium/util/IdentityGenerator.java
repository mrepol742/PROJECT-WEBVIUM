/*
 *
 *
 *
 * DROID MJ Property || Confidential
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 */

package com.droidmj.webvium.util;

import com.droidmj.webvium.annotation.release.Keep;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

public class IdentityGenerator {
    private static final String[] male = {
            "James",
            "John",
            "Robert",
            "Michael",
            "William",
            "David",
            "Richard",
            "Joseph",
            "Thomas",
            "Charles",
            "Christopher",
            "Daniel",
            "Matthew",
            "Anthony",
            "Donald",
            "Mark",
            "Paul",
            "Steven",
            "Andrew",
            "Kenneth",
            "Joshua",
            "George",
            "Kevin",
            "Brian",
            "Edward",
            "Ronald",
            "Timothy",
            "Jason",
            "Jeffrey",
            "Ryan",
            "Jacob",
            "Gary",
            "Nicholas",
            "Eric",
            "Stephen",
            "Jonathan",
            "Larry",
            "Justin",
            "Scott",
            "Brandon",
            "Frank",
            "Benjamin",
            "Gregory",
            "Samuel",
            "Raymond",
            "Patrick",
            "Alexander",
            "Jack",
            "Deniss",
            "Jerry",
            "Tyler",
            "Aaron",
            "Jose",
            "Henry",
            "Douglas",
            "Adam",
            "Peter",
            "Nathan",
            "Zachary",
            "Walter",
            "Kyle",
            "Harold",
            "Carl",
            "Jeremy",
            "Keith",
            "Roger",
            "Gerald",
            "Ethan",
            "Arthur",
            "Terry",
            "Christian",
            "Sean",
            "Lawrence",
            "Austin",
            "Joe",
            "Noah",
            "Jesse",
            "Albert",
            "Bryan",
            "Billy",
            "Bruce",
            "Willie",
            "Jordan",
            "Dylan",
            "Alan",
            "Ralph",
            "Gabriel",
            "Roy",
            "Juan",
            "Wayne",
            "Eugene",
            "Logan",
            "Randy",
            "Louis",
            "Russell",
            "Vincent",
            "Philip",
            "Bobby",
            "Johnny",
            "Bradley"
    };
    private static final String[] female = {
            "Mary",
            "Patricia",
            "Jennifer",
            "Linda",
            "Elizabeth",
            "Barbara",
            "Susan",
            "Jessica",
            "Sarah",
            "Karen",
            "Nancy",
            "Margaret",
            "Lisa",
            "Betty",
            "Dorothy",
            "Sandra",
            "Ashley",
            "Kimberly",
            "Donna",
            "Emily",
            "Michelle",
            "Carol",
            "Amanda",
            "Melissa",
            "Deborah",
            "Stephanie",
            "Rebecca",
            "Laura",
            "Sharon",
            "Cynthia",
            "Kathleen",
            "Helen",
            "Amy",
            "Shirley",
            "Angela",
            "Anna",
            "Brenda",
            "Pamela",
            "Nicole",
            "Ruth",
            "Katherine",
            "Samantha",
            "Christine",
            "Emma",
            "Catherine",
            "Debra",
            "Virginia",
            "Rachel",
            "Carolyn",
            "Janet",
            "Marzia",
            "Heather",
            "Diane",
            "Julie",
            "Joyce",
            "Victoria",
            "Kelly",
            "Christina",
            "Joan",
            "Evely",
            "Lauren",
            "Judith",
            "Olivia",
            "Frances",
            "Martha",
            "Cheryl",
            "Megan",
            "Andrea",
            "Hannah",
            "Jacqueline",
            "Ann",
            "Jean",
            "Alice",
            "Kathryn",
            "Gloria",
            "Teresa",
            "Doris",
            "Sara",
            "Janice",
            "Julia",
            "Marie",
            "Madison",
            "Grace",
            "Judy",
            "Theresa",
            "Beverly",
            "Denise",
            "Marilyn",
            "Amber",
            "Danielle",
            "Abigail",
            "Brittany",
            "Rose",
            "Diana",
            "Natalie",
            "Sophia",
            "Alexis",
            "Lori",
            "Kayla",
            "Jane"
    };
    private static final String[] countries = {
            "AF|Afghanistan",
            "AL|Albania",
            "DZ|Algeria",
            "AS|American Samoa",
            "AD|Andorra",
            "AO|Angola",
            "AI|Anguilla",
            "AQ|Antarctica",
            "AG|Antigua And Barbuda",
            "AR|Argentina",
            "AM|Armenia",
            "AW|Aruba",
            "AU|Australia",
            "AT|Austria",
            "AZ|Azerbaijan",
            "BS|Bahamas",
            "BH|Bahrain",
            "BD|Bangladesh",
            "BB|Barbados",
            "BY|Belarus",
            "BE|Belgium",
            "BZ|Belize",
            "BJ|Benin",
            "BM|Bermuda",
            "BT|Bhutan",
            "BO|Bolivia",
            "BA|Bosnia And Herzegovina",
            "BW|Botswana",
            "BV|Bouvet Island",
            "BR|Brazil",
            "IO|British Indian Ocean Territory",
            "BN|Brunei Darussalam",
            "BG|Bulgaria",
            "BF|Burkina Faso",
            "BI|Burundi",
            "KH|Cambodia",
            "CM|Cameroon",
            "CA|Canada",
            "CV|Cape Verde",
            "KY|Cayman Islands",
            "CF|Central African Republic",
            "TD|Chad",
            "CL|Chile",
            "CN|China",
            "CX|Christmas Island",
            "CC|Cocos (keeling) Islands",
            "CO|Colombia",
            "KM|Comoros",
            "CG|Congo",
            "CD|Congo, The Democratic Republic Of The",
            "CK|Cook Islands",
            "CR|Costa Rica",
            "CI|Cote D'ivoire",
            "HR|Croatia",
            "CU|Cuba",
            "CY|Cyprus",
            "CZ|Czech Republic",
            "DK|Denmark",
            "DJ|Djibouti",
            "DM|Dominica",
            "DO|Dominican Republic",
            "TP|East Timor",
            "EC|Ecuador",
            "EG|Egypt",
            "SV|El Salvador",
            "GQ|Equatorial Guinea",
            "ER|Eritrea",
            "EE|Estonia",
            "ET|Ethiopia",
            "FK|Falkland Islands (malvinas)",
            "FO|Faroe Islands",
            "FJ|Fiji",
            "FI|Finland",
            "FR|France",
            "GF|French Guiana",
            "PF|French Polynesia",
            "TF|French Southern Territories",
            "GA|Gabon",
            "GM|Gambia",
            "GE|Georgia",
            "DE|Germa ny",
            "GH|Ghana",
            "GI|Gibraltar",
            "GR|Greece",
            "GL|Greenland",
            "GD|Grenada",
            "GP|Guadeloupe",
            "GU|Guam",
            "GT|Guatemala",
            "GN|Guinea",
            "GW|Guinea-bissau",
            "GY|Guyana",
            "HT|Haiti",
            "HM|Heard Island And Mcdonald Islands",
            "VA|Holy See (vatican City State)",
            "HN|Honduras",
            "HK|Hong Kong",
            "HU|Hungary",
            "IS|Iceland",
            "IN|India",
            "ID|Indonesia",
            "IR|Iran, Islamic Republic Of",
            "IQ|Iraq",
            "IE|Ireland",
            "IL|Israel",
            "IT|Italy",
            "JM|Jamaica",
            "JP|Japan",
            "JO|Jordan",
            "KZ|Kazakstan",
            "KE|Kenya",
            "KI|Kiribati",
            "KP|Korea, Democratic People's Republic Of",
            "KR|Korea, Republic Of",
            "KV|Kosovo",
            "KW|Kuwait",
            "KG|Kyrgyzstan",
            "LA|Lao People's Democratic Republic",
            "LV|Latvia",
            "LB|Lebanon",
            "LS|Lesotho",
            "LR|Liberia",
            "LY|Libyan Arab Jamahiriya",
            "LI|Liechtenstein",
            "LT|Lithuania",
            "LU|Luxembourg",
            "MO|Macau",
            "MK|Macedonia, The Former Yugoslav Republic Of",
            "MG|Madagascar",
            "MW|Malawi",
            "MY|Malaysia",
            "MV|Maldives",
            "ML|Mali",
            "MT|Malta",
            "MH|Marshall Islands",
            "MQ|Martinique",
            "MR|Mauritania",
            "MU|Mauritius",
            "YT|Mayotte",
            "MX|Mexico",
            "FM|Micronesia, Federated States Of",
            "MD|Moldova, Republic Of",
            "MC|Monaco",
            "MN|Mongolia",
            "MS|Montserrat",
            "ME|Montenegro",
            "MA|Morocco",
            "MZ|Mozambique",
            "MM|Myanmar",
            "NA|Namibia",
            "NR|Nauru",
            "NP|Nepal",
            "NL|Netherlands",
            "AN|Netherlands Antilles",
            "NC|New Caledonia",
            "NZ|New Zealand",
            "NI|Nicaragua",
            "NE|Niger",
            "NG|Nigeria",
            "NU|Niue",
            "NF|Norfolk Island",
            "MP|Northern Mariana Islands",
            "NO|Norway",
            "OM|Oman",
            "PK|Pakistan",
            "PW|Palau",
            "PS|Palestinian Territory, Occupied",
            "PA|Panama",
            "PG|Papua New Guinea",
            "PY|Paraguay",
            "PE|Peru",
            "PH|Philippines",
            "PN|Pitcairn",
            "PL|Poland",
            "PT|Portugal",
            "PR|Puerto Rico",
            "QA|Qatar",
            "RE|Reunion",
            "RO|Romania",
            "RU|Russian Federation",
            "RW|Rwanda",
            "SH|Saint Helena",
            "KN|Saint Kitts And Nevis",
            "LC|Saint Lucia",
            "PM|Saint Pierre And Miquelon",
            "VC|Saint Vincent And The Grenadines",
            "WS|Samoa",
            "SM|San Marino",
            "ST|Sao Tome And Principe",
            "SA|Saudi Arabia",
            "SN|Senegal",
            "RS|Serbia",
            "SC|Seychelles",
            "SL|Sierra Leone",
            "SG|Singapore",
            "SK|Slovakia",
            "SI|Slovenia",
            "SB|Solomon Islands",
            "SO|Somalia",
            "ZA|South Africa",
            "GS|South Georgia And The South Sandwich Islands",
            "ES|Spain",
            "LK|Sri Lanka",
            "SD|Sudan",
            "SR|Suriname",
            "SJ|Svalbard And Jan Mayen",
            "SZ|Swaziland",
            "SE|Sweden",
            "CH|Switzerland",
            "SY|Syrian Arab Republic",
            "TW|Taiwan, Province Of China",
            "TJ|Tajikistan",
            "TZ|Tanzania, United Republic Of",
            "TH|Thailand",
            "TG|Togo",
            "TK|Tokelau",
            "TO|Tonga",
            "TT|Trinidad And Tobago",
            "TN|Tunisia",
            "TR|Turkey",
            "TM|Turkmenistan",
            "TC|Turks And Caicos Islands",
            "TV|Tuvalu",
            "UG|Uganda",
            "UA|Ukraine",
            "AE|United Arab Emirates",
            "GB|United Kingdom",
            "US|United States",
            "UM|United States Minor Outlying Islands",
            "UY|Uruguay",
            "UZ|Uzbekistan",
            "VU|Vanuatu",
            "VE|Venezuela",
            "VN|Viet Nam",
            "VG|Virgin Islands, British",
            "VI|Virgin Islands, U.s.",
            "WF|Wallis And Futuna",
            "EH|Western Sahara",
            "YE|Yemen",
            "ZM|Zambia",
            "ZW|Zimbabwe"
    };
    private static final String[] status = {
            "Single",
            "In A Relationship",
            "Engaged",
            "Married",
            "In A Civil Union",
            "In A Domestic Partnership",
            "In A Open Relationship"
    };
    private static final String[] month = {
            "January",
            "February",
            "March",
            "April",
            "May",
            "June",
            "July",
            "August",
            "September",
            "October",
            "November",
            "December"
    };
    private static final String[] eyeColor = {
            "Brown",
            "Blue"
    };
    private static final String[] religion = {
            "Catholic",
            "Christian",
            "Islam",
            "Roman Catholic"
    };
    private static final String[] hobbies = {"3D Modeling", "Acting", "Animation", "Arcade Games", "Art", "Badminton", "Baking", "Baseball", "Basketball", "Blogging", "Coding", "Comedy", "Comics", "Cosplay", "Dance", "Drawing", "Fishing", "Fitness", "Golf", "Hiking", "Illustration", "Learning", "Learning to Code", "Listening to Music", "Memes", "Napping", "Photography", "Reading", "Shopping", "Singing", "Sleeping", "Studying", "Swimming", "Video Games", "Watching Movies", "Watching Television", "Writting"};

    @Keep
    private IdentityGenerator() {
    }

    public static String a(String data) {
        try {

            Random d = new Random();
            int e = d.nextInt(2);
            int f = d.nextInt(99);
            int g = d.nextInt(99);
            int h = d.nextInt(99);
            int i = d.nextInt(60 - 18) + 18;
            int j = countries.length;
            int k = d.nextInt(j);
            int l = d.nextInt(7);
            int m = d.nextInt(12);
            int o = d.nextInt(28 - 1) + 1;
            int p = d.nextInt(2);
            int q = d.nextInt(8 - 5) + 5;
            int r = d.nextInt(9);
            String s = q + "." + r;
            int t = d.nextInt(24 - 18) + 18;
            int u = d.nextInt(9 - 5) + 5;
            String v = t + "." + u;
            int w = d.nextInt(99);
            int x = d.nextInt(99);
            int y = d.nextInt(99);
            int z = d.nextInt(11);
            int a1 = d.nextInt(99);
            int a2 = d.nextInt(hobbies.length);
            SimpleDateFormat sdf = new SimpleDateFormat("yy", Locale.US);
            int date = Integer.parseInt(sdf.format(new Date()));
            if (e == 0) {
                return data.replace("%a", male[f])
                        .replace("%b", male[g])
                        .replace("%c", male[h])
                        .replace("%d", male[f])
                        .replace("%e", male[g])
                        .replace("%f", male[h])
                        .replace("%g", month[m] + " " + o + " " + (date - i))
                        .replace("%h", "Male")
                        .replace("%i", Integer.toString(i))
                        .replace("%j", status[l])
                        .replace("%k", countries[k])
                        .replace("%l", eyeColor[p])
                        .replace("%m", s)
                        .replace("%n", v)
                        .replace("%o", hobbies[a2])
                        .replace("%p", "Women")
                        .replace("%q", religion[z])
                        .replace("%r", female[w] + " " + female[x] + " " + male[g])
                        .replace("%s", male[y] + " " + female[a1] + " " + male[h]);

            }
            return data.replace("%a", female[f])
                    .replace("%b", female[g])
                    .replace("%c", male[h])
                    .replace("%d", female[f])
                    .replace("%e", female[g])
                    .replace("%f", male[h])
                    .replace("%g", month[m] + " " + o + " " + (date - i))
                    .replace("%h", "Female")
                    .replace("%i", Integer.toString(i))
                    .replace("%j", status[l])
                    .replace("%k", countries[k])
                    .replace("%l", eyeColor[p])
                    .replace("%m", s)
                    .replace("%n", v)
                    .replace("%o", hobbies[a2])
                    .replace("%p", "Men")
                    .replace("%q", religion[z])
                    .replace("%r", female[w] + " " + female[x] + " " + female[g])
                    .replace("%s", male[y] + " " + female[a1] + " " + male[h]);

        } catch (Exception ex) {
            return a(data);
        }
    }

}