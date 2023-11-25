extends Node

signal on_battery_permission_request_completed

var ln = null

func init():
	if Engine.has_singleton("BatteryOptimization"):
		ln = Engine.get_singleton("BatteryOptimization")
		init_signals()

func init_signals():
	ln.connect("permission_battery_request_completed", Callable(self, "_permission_battery_request_completed"))

func _permission_battery_request_completed(text: String):
	emit_signal("on_battery_permission_request_completed")
	print(text)

func isPermissionGranted() -> bool:
	if not ln:
		not_found_plugin()
		return false
	return ln.isPermissionGranted()


func requestPermission():
	if not ln:
		not_found_plugin()
		return
	ln.requestBatteryPermission()


func not_found_plugin():
	print('[BatterOptimization] Not found plugin. Please ensure that you checked BatterOptimization plugin in the export template')
