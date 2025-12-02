import 'package:flutter/material.dart';
import 'package:sampleapp/widgets/bottom_sheet_ui_model.dart';

class CommandsBottomSheetWidget extends StatefulWidget {
  final List<BottomSheetUiModel> uiModels;
  final Function(CommandUiModel) onSelectCommand;

  const CommandsBottomSheetWidget({
    super.key,
    required this.uiModels,
    required this.onSelectCommand,
  });

  @override
  State<StatefulWidget> createState() => _CommandsBottomSheetWidget();
}

class _CommandsBottomSheetWidget extends State<CommandsBottomSheetWidget>
    with SingleTickerProviderStateMixin {
  late AnimationController _controller;

  @override
  void initState() {
    super.initState();
    _controller = AnimationController(
      vsync: this,
      duration: const Duration(microseconds: 400),
    )..forward();
  }

  @override
  void dispose() {
    _controller.dispose();
    super.dispose();
  }

  @override
  Widget build(BuildContext context) {
    return BottomSheet(
      animationController: _controller,
      onClosing: () {},
      builder: (context) {
        return Container(
          height: 200,
          padding: const EdgeInsets.all(16),
          decoration: const BoxDecoration(
            color: Colors.white,
            borderRadius: BorderRadius.vertical(top: Radius.circular(16)),
            boxShadow: [BoxShadow(blurRadius: 6, color: Colors.black26)],
          ),
          child: _buildMainContent(context),
        );
      },
    );
  }

  Widget _buildMainContent(BuildContext context) {
    if (widget.uiModels.isEmpty) {
      return SizedBox(
        height: double.maxFinite,
        width: double.infinity,
        child: Text(
          'Select any element',
          style: Theme.of(context).textTheme.headlineMedium,
        ),
      );
    } else {
      return ListView.builder(
        itemCount: widget.uiModels.length,
        itemBuilder: (context, index) => _buildBottomSheetItem(
          context,
          widget.uiModels[index],
        ),
      );
    }
  }

  Widget _buildBottomSheetItem(
    BuildContext context,
    BottomSheetUiModel uiModel,
  ) {
    switch (uiModel) {
      case SelectedNodeUiModel():
        return ListTile(
          title: Text(
            'Selected ${uiModel.nodeIdName}',
            style: Theme.of(context).textTheme.headlineMedium,
          ),
        );
      case CommandUiModel():
        return ListTile(
          title: OutlinedButton(
            onPressed: () => {widget.onSelectCommand(uiModel)},
            child: Text(uiModel.commandName),
          ),
        );
    }
  }
}
