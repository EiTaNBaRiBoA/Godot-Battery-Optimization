# Godot Battery Optimization

The is godot android plugin is for entering battery optimizations settings on each mobile and navigate the user to the relevant screens so that they can remove battery or optimization restrictions for the app. 

## Installation

Download For godot and put the batteryOptimization.gd in AutoLoad in godot and aar,gdap to the android\plugins. Enjoy! Credit to [WaseemSabir](https://github.com/WaseemSabir/BatteryPermissionHelper) for the logic.

## Usage

```GDscript
func _ready():
	BatteryOptimization.init()

func BatteryOptimizationMethodRequest() -> void:
	if(!BatteryOptimization.isPermissionGranted()):
		BatteryOptimization.requestPermission()
		await BatteryOptimization.on_battery_permission_request_completed
```
