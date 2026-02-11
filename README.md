## Overview

Small utility mod that allows you to "counterfeit" the system date, for several possible purposes:

- To test or experience festival content without changing the date at system level
- To disable festival content in certain mods by faking a non-festival date

## Configuration

The configuration file can be found at `config/fakedate.toml`:

```
#Specifies the year to fake (0 = disable)
fakeYear = 0
#Specifies the month to fake (0 = disable, 1 = January, 2 = February, ...)
fakeMonth = 0
#Specifies the day to fake (0 = disable)
fakeDayOfMonth = 0
#Only fake date when queried by specified mods
#Use * to allow any (not recommended)
filter = ["enter_modid_here"]
```